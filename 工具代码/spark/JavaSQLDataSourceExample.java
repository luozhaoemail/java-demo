package demo;

// $example on:schema_merging$
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// $example off:schema_merging$
import java.util.Properties;

// $example on:basic_parquet_example$
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoders;
// $example on:schema_merging$
// $example on:json_dataset$
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off:json_dataset$
// $example off:schema_merging$
// $example off:basic_parquet_example$
import org.apache.spark.sql.SparkSession;
/**
 * Spark SQL通过DataFrame接口支持各种不同数据源的操作。
 * 一个DataFrame可以进行相关的转换操作，也可以用于创建临时视图。
 * 注册DataFrame为一个临时视图可以允许你对其数据执行SQL查询。
 * 本文首先会介绍使用Spark数据源加载和保存数据的一般方法，然后对内置数据源进行详细介绍。 
 */
public class JavaSQLDataSourceExample {

  // $example on:schema_merging$
  public static class Square implements Serializable {
    private int value;
    private int square;

    // Getters and setters...
    // $example off:schema_merging$
    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getSquare() {
      return square;
    }

    public void setSquare(int square) {
      this.square = square;
    }
    // $example on:schema_merging$
  }
  // $example off:schema_merging$

  // $example on:schema_merging$
  public static class Cube implements Serializable {
    private int value;
    private int cube;

    // Getters and setters...
    // $example off:schema_merging$
    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getCube() {
      return cube;
    }

    public void setCube(int cube) {
      this.cube = cube;
    }
    // $example on:schema_merging$
  }
  // $example off:schema_merging$

  public static void main(String[] args) {
    SparkSession spark = SparkSession
      .builder()
      .appName("Java Spark SQL data sources example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate();

    runBasicDataSourceExample(spark);
    runBasicParquetExample(spark);
    runParquetSchemaMergingExample(spark);
    runJsonDatasetExample(spark);
    runJdbcDatasetExample(spark);

    spark.stop();
  }

  /**一般的Load/Save方法
   Spark SQL最简单的也是默认的数据源格式是Parquet(除非使用了spark.sql.sources.default配置修改)，它将会被用于所有的操作。
      如果是使用windows系统的话，需要把对应版本编译的hadoop.dll复制到C:\Windows\System32
   */
  private static void runBasicDataSourceExample(SparkSession spark) {
   
    Dataset<Row> usersDF = spark.read().load("examples/src/main/resources/users.parquet");
    usersDF.select("name", "favorite_color").write().save("namesAndFavColors.parquet");
    
    /** 手动指定选项
     	内置的数据源类型:json, parquet, jdbc, orc, libsvm, csv, text,这些类型可以互相转换     	 
     */
    Dataset<Row> peopleDF = spark.read().format("json").load("examples/src/main/resources/people.json");
    peopleDF.select("name", "age").write().format("parquet").save("namesAndAges.parquet");
    /**保存模式:ErrorIfExists(默认)  Append  Overwrite Ignore 
    	peopleDF.select("name", "age").write().mode(SaveMode.Append).format("parquet").save("namesAndAges.parquet");            
     	
     */
    
    //也可以直接执行SQL查询而不需要使用API加载文件为DataFrame然后再查询
    Dataset<Row> sqlDF =spark.sql("SELECT * FROM parquet.`examples/src/main/resources/users.parquet`");
    
  }

  /**Parquet文件：纵列格式  
   */
  private static void runBasicParquetExample(SparkSession spark) {
    // $example on:basic_parquet_example$
    Dataset<Row> peopleDF = spark.read().json("examples/src/main/resources/people.json");

    // DataFrames can be saved as Parquet files, maintaining the schema information
    peopleDF.write().parquet("people.parquet");

    // Read in the Parquet file created above.
    // Parquet files are self-describing so the schema is preserved
    // The result of loading a parquet file is also a DataFrame
    Dataset<Row> parquetFileDF = spark.read().parquet("people.parquet");

    // Parquet files can also be used to create a temporary view and then used in SQL statements
    parquetFileDF.createOrReplaceTempView("parquetFile");
    Dataset<Row> namesDF = spark.sql("SELECT name FROM parquetFile WHERE age BETWEEN 13 AND 19");
    Dataset<String> namesDS = namesDF.map(new MapFunction<Row, String>() {
      public String call(Row row) {
        return "Name: " + row.getString(0);
      }
    }, Encoders.STRING());
    namesDS.show();
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+
    // $example off:basic_parquet_example$
  }

  /** Schema合并：先定义一个简单的schema，然后根据需要逐渐地向schema中增加列
   *  Schema合并是一个相对高消耗,默认关闭，需要手动开启：
 		设置数据源选项option("mergeSchema", true)
   */
  private static void runParquetSchemaMergingExample(SparkSession spark) {
    // $example on:schema_merging$
    List<Square> squares = new ArrayList<>();
    for (int value = 1; value <= 5; value++) {
      Square square = new Square();
      square.setValue(value);
      square.setSquare(value * value);
      squares.add(square);
    }

    // Create a simple DataFrame, store into a partition directory
    Dataset<Row> squaresDF = spark.createDataFrame(squares, Square.class);
    squaresDF.write().parquet("data/test_table/key=1");

    List<Cube> cubes = new ArrayList<>();
    for (int value = 6; value <= 10; value++) {
      Cube cube = new Cube();
      cube.setValue(value);
      cube.setCube(value * value * value);
      cubes.add(cube);
    }

    // Create another DataFrame in a new partition directory,
    // adding a new column and dropping an existing column
    Dataset<Row> cubesDF = spark.createDataFrame(cubes, Cube.class);
    cubesDF.write().parquet("data/test_table/key=2");

    // Read the partitioned table
    Dataset<Row> mergedDF = spark.read().option("mergeSchema", true).parquet("data/test_table");
    mergedDF.printSchema();

    // The final schema consists of all 3 columns in the Parquet files together
    // with the partitioning column appeared in the partition directory paths
    // root
    //  |-- value: int (nullable = true)
    //  |-- square: int (nullable = true)
    //  |-- cube: int (nullable = true)
    //  |-- key: int (nullable = true)
    // $example off:schema_merging$
  }

  
  private static void runJsonDatasetExample(SparkSession spark) {
    // $example on:json_dataset$
    // A JSON dataset is pointed to by path.
    // The path can be either a single text file or a directory storing text files
    Dataset<Row> people = spark.read().json("examples/src/main/resources/people.json");

    // The inferred schema can be visualized using the printSchema() method
    people.printSchema();
    // root
    //  |-- age: long (nullable = true)
    //  |-- name: string (nullable = true)

    // Creates a temporary view using the DataFrame
    people.createOrReplaceTempView("people");

    // SQL statements can be run by using the sql methods provided by spark
    Dataset<Row> namesDF = spark.sql("SELECT name FROM people WHERE age BETWEEN 13 AND 19");
    namesDF.show();
    // +------+
    // |  name|
    // +------+
    // |Justin|
    // +------+

    // Alternatively, a DataFrame can be created for a JSON dataset represented by
    // an RDD[String] storing one JSON object per string.
    List<String> jsonData = Arrays.asList(
            "{\"name\":\"Yin\",\"address\":{\"city\":\"Columbus\",\"state\":\"Ohio\"}}");
    
    JavaRDD<String> anotherPeopleRDD = new JavaSparkContext(spark.sparkContext()).parallelize(jsonData);
    Dataset anotherPeople = spark.read().json(anotherPeopleRDD);
    anotherPeople.show();
    // +---------------+----+
    // |        address|name|
    // +---------------+----+
    // |[Columbus,Ohio]| Yin|
    // +---------------+----+
    // $example off:json_dataset$
  }
  
 /**用JDBC访问其他数据库
 	url  jdbc:postgresql://localhost/test?user=fred&password=secret
 	dbtable  The JDBC table that should be read
 	driver   The class name of the JDBC driver to use to connect to this URL.  
  */
  private static void runJdbcDatasetExample(SparkSession spark) {
    // Note: JDBC loading and saving can be achieved via either the load/save or jdbc methods
    // Loading data from a JDBC source 方法1
    Dataset<Row> jdbcDF = spark.read()
      .format("jdbc")
      .option("url", "jdbc:postgresql:dbserver")
      .option("dbtable", "schema.tablename")
      .option("user", "username")
      .option("password", "password")
      .load();

    //方法2
    Properties connectionProperties = new Properties();
    connectionProperties.put("user", "username");
    connectionProperties.put("password", "password");
    Dataset<Row> jdbcDF2 = spark.read().jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);

    // Saving data to a JDBC source
    jdbcDF.write()
      .format("jdbc")
      .option("url", "jdbc:postgresql:dbserver")
      .option("dbtable", "schema.tablename")
      .option("user", "username")
      .option("password", "password")
      .save();

    jdbcDF2.write().jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties);

  }
}
