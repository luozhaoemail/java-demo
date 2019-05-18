package demo;

// $example on:programmatic_schema$
import java.util.ArrayList;
import java.util.List;
// $example off:programmatic_schema$
// $example on:create_ds$
import java.util.Arrays;
import java.util.Collections;
import java.io.Serializable;
// $example off:create_ds$

// $example on:schema_inferring$
// $example on:programmatic_schema$
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
// $example off:programmatic_schema$
// $example on:create_ds$
import org.apache.spark.api.java.function.MapFunction;
// $example on:create_df$
// $example on:run_sql$
// $example on:programmatic_schema$
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off:programmatic_schema$
// $example off:create_df$
// $example off:run_sql$
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
// $example off:create_ds$
// $example off:schema_inferring$
import org.apache.spark.sql.RowFactory;
// $example on:init_session$
import org.apache.spark.sql.SparkSession;
// $example off:init_session$
// $example on:programmatic_schema$
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
// $example off:programmatic_schema$
import org.apache.spark.sql.AnalysisException;

// $example on:untyped_ops$
// col("...") is preferable to df.col("...")
import static org.apache.spark.sql.functions.col;
// $example off:untyped_ops$

public class JavaSparkSQLExample {

  public static class Person implements Serializable {
    private String name;
    private int age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }


  public static void main(String[] args) throws AnalysisException {
          
    SparkSession spark = SparkSession  
    	    .builder()  
    	    .appName("Java Spark SQL basic example")  
    	    .master("local[*]")  //local[*]，表示本机多线程，线程数与服务器核数相同
    	    .config("spark.some.config.option", "some-value")  
    	    .getOrCreate();  
   

    runBasicDataFrameExample(spark);
    runDatasetCreationExample(spark);
    runInferSchemaExample(spark);
    runProgrammaticSchemaExample(spark);

    spark.stop();
  }

  //创建DataFrames,读取一个JSON文件创建一个DataFrame，然后调用show()方法显示内容
  private static void runBasicDataFrameExample(SparkSession spark) throws AnalysisException {
    
    Dataset<Row> df = spark.read().json("examples/src/main/resources/people.json");

    // Displays the content of the DataFrame to stdout  显示DataFrame的内容  
    df.show();
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+


    //非类型化数据集操作(DataFrame操作) 
    // Print the schema in a tree format 以树形格式打印schema 
    df.printSchema();
    // root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)

    // Select only the "name" column 选择“name”列 
    df.select("name").show();
    // +-------+
    // |   name|
    // +-------+
    // |Michael|
    // |   Andy|
    // | Justin|
    // +-------+

    // Select everybody, but increment the age by 1
    df.select(col("name"), col("age").plus(1)).show();
    // +-------+---------+
    // |   name|(age + 1)|
    // +-------+---------+
    // |Michael|     null|
    // |   Andy|       31|
    // | Justin|       20|
    // +-------+---------+

    // Select people older than 21 选择年龄“age”大于21的people  
    df.filter(col("age").gt(21)).show();
    // +---+----+
    // |age|name|
    // +---+----+
    // | 30|Andy|
    // +---+----+

    // Count people by age 根据年龄“age”分组并计数 
    df.groupBy("age").count().show();
    // +----+-----+
    // | age|count|
    // +----+-----+
    // |  19|    1|
    // |null|    1|
    // |  30|    1|
    // +----+-----+
 

    /** Register the DataFrame as a SQL temporary view 注册DataFrame为一个SQL的临时视图  
        Spark SQL的临时视图是当前session有效的，也就是视图会与创建该视图的session终止而失效。
     */
    df.createOrReplaceTempView("people");

    Dataset<Row> sqlDF = spark.sql("SELECT * FROM people");
    sqlDF.show();
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+

    /**全局临时视图是与系统保留数据库global_temp绑定，一直有效的直到Spark应用终止才失效的临时视图
              以使用的时候必须使用该名字去引用，例如，SELECT * FROM global_temp.view1。
  
     */
    // Register the DataFrame as a global temporary view
    df.createGlobalTempView("people");
    
    // 全局临时视图与系统保留数据库global_temp绑定  
    spark.sql("SELECT * FROM global_temp.people").show();
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+

    // 全局临时视图是跨session的 
    spark.newSession().sql("SELECT * FROM global_temp.people").show();
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+
  
  }

  //创建Datasets类似于RDDs，然而，Datasets使用了一个专门的编码器Encoder来序列化对象而不是使用Java的序列化
  private static void runDatasetCreationExample(SparkSession spark) {
   
    Person person = new Person();
    person.setName("Andy");
    person.setAge(32);

    // 创建Java beans的Encoders 
    Encoder<Person> personEncoder = Encoders.bean(Person.class);    
    Dataset<Person> javaBeanDS = spark.createDataset(Collections.singletonList(person), personEncoder);
    javaBeanDS.show();
    // +---+----+
    // |age|name|
    // +---+----+
    // | 32|Andy|
    // +---+----+

    
    //Encoders类提供了常见类型的Encoders 
    Encoder<Integer> integerEncoder = Encoders.INT();
    Dataset<Integer> primitiveDS = spark.createDataset(Arrays.asList(1, 2, 3), integerEncoder);
    Dataset<Integer> transformedDS = primitiveDS.map(new MapFunction<Integer, Integer>() {
      @Override
      public Integer call(Integer value) throws Exception {
        return value + 1;
      }
    }, integerEncoder);
    transformedDS.collect(); // Returns [2, 3, 4]
 
    //通过指定Encoder转换DataFrames为Dataset，基于名字匹配 
    String path = "examples/src/main/resources/people.json";
    Dataset<Person> peopleDS = spark.read().json(path).as(personEncoder);
    peopleDS.show();
    // +----+-------+
    // | age|   name|
    // +----+-------+
    // |null|Michael|
    // |  30|   Andy|
    // |  19| Justin|
    // +----+-------+
    // $example off:create_ds$
  }

  /**Datasets与RDDs的相互转换
   * 第一种是使用反射获取RDD的Schema，当知道schema的时候，使用基于反射的方法会让代码更加简明而且效果也更好。
   * 第二种是通过编程接口指定schema，这种方法会使代码冗长，但是可以在运行时才知道数据列以及其类型的情况下事先构造Datasets。
   * 下面是第一种：使用反射获取Schema ：Spark SQL支持将JavaBean的RDD自动转换成DataFrame。
   * @param spark
   */
  private static void runInferSchemaExample(SparkSession spark) {
   
    //通过一个文本文件创建Person对象的RDD 
    JavaRDD<Person> peopleRDD = spark.read()
      .textFile("examples/src/main/resources/people.txt")
      .javaRDD()
      .map(new Function<String, Person>() {
        @Override
        public Person call(String line) throws Exception {//Justin, 19 为一行数据
          String[] parts = line.split(",");
          Person person = new Person();
          person.setName(parts[0]);
          person.setAge(Integer.parseInt(parts[1].trim()));
          return person;
        }
      });

    //对JavaBeans的RDD指定schema得到DataFrame  
    Dataset<Row> peopleDF = spark.createDataFrame(peopleRDD, Person.class);    
    peopleDF.createOrReplaceTempView("people");//注册该DataFrame为临时视图  

    // SQL statements can be run by using the sql methods provided by spark 执行SQL语句
    Dataset<Row> teenagersDF = spark.sql("SELECT name FROM people WHERE age BETWEEN 13 AND 19");

    
    // The columns of a row in the result can be accessed by field index 结果中的列可以通过属性的下标获取 
    Encoder<String> stringEncoder = Encoders.STRING();
    
    Dataset<String> teenagerNamesByIndexDF = teenagersDF.map(new MapFunction<Row, String>() {
      @Override
      public String call(Row row) throws Exception {
        return "Name: " + row.getString(0);//下标从0开始
      }
    }, stringEncoder);
    
    teenagerNamesByIndexDF.show();
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+

    //或者通过属性的名字获取 
    Dataset<String> teenagerNamesByFieldDF = teenagersDF.map(new MapFunction<Row, String>() {
      @Override
      public String call(Row row) throws Exception {
        return "Name: " + row.<String>getAs("name");//获取字段名
      }
    }, stringEncoder);
    
    teenagerNamesByFieldDF.show();
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+
  }

  /**
        通过编程接口指定Schema:
        当JavaBean不能被事先定义的时候，通过编程创建Dataset<Row>需要三个步骤：
	通过原来的RDD创建一个Rows格式的RDD
	创建以StructType表现的schema，该StructType与步骤1创建的Rows结构RDD相匹配
	通过SparkSession的createDataFrame方法对Rows格式的RDD指定schema
   */
  private static void runProgrammaticSchemaExample(SparkSession spark) {
    
    // Create an RDD
    JavaRDD<String> peopleRDD = spark.sparkContext()
      .textFile("examples/src/main/resources/people.txt", 1)
      .toJavaRDD();

    //使用string定义schema 
    String schemaString = "name age";

    //基于用字符串定义的schema生成StructType 
    List<StructField> fields = new ArrayList<>();
    
    for (String fieldName : schemaString.split(" ")) 
    {
      StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
      fields.add(field);
    }
    
    StructType schema = DataTypes.createStructType(fields);

    //把RDD (people)转换为Rows  
    JavaRDD<Row> rowRDD = peopleRDD.map(new Function<String, Row>() {
      @Override
      public Row call(String record) throws Exception {
        String[] attributes = record.split(",");
        return RowFactory.create(attributes[0], attributes[1].trim());
      }
    });

    // Apply the schema to the RDD 对RDD应用schema  
    Dataset<Row> peopleDataFrame = spark.createDataFrame(rowRDD, schema);

    // 使用DataFrame创建临时视图 
    peopleDataFrame.createOrReplaceTempView("people");

    // 运行SQL查询
    Dataset<Row> results = spark.sql("SELECT name FROM people");

    // SQL查询的结果是DataFrames类型，支持所有一般的RDD操作  
    // 结果的列可以通过属性的下标或者名字获取 
    Dataset<String> namesDS = results.map(new MapFunction<Row, String>() {
      @Override
      public String call(Row row) throws Exception {
        return "Name: " + row.getString(0);
      }
    }, Encoders.STRING());
    namesDS.show();
    // +-------------+
    // |        value|
    // +-------------+
    // |Name: Michael|
    // |   Name: Andy|
    // | Name: Justin|
    // +-------------+
  
  }
}
