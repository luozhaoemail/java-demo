package design_pattern;

/**
4.建造者模式（Builder）
避免创建多个不同参数的Student构造函数，使用StudentBuilder来灵活的组装参数。
适用于初始化参数比较多且不确定的情况下。
 */
public class Test_Build {
	public static void main(String[] args) {
		 Student a = new Student.StudentBuilder()
						 .setAge(13)
						 .setName("LiHua")
						 .build();
		 
	     Student b = new Student.StudentBuilder()
					     .setSchool("sc")
					     .setSex("Male")
					     .setName("ZhangSan")
					     .build();
	}
}



class Student
{	
	 private String name = null ;
	 private int number = -1 ;
	 private String sex = null ;
	 private int age = -1 ;
	 private String school = null ;
	
	 
	 public Student(StudentBuilder builder)
	 {
         this.age = builder.age;
         this.name = builder.name;
         this.number = builder.number;
         this.school = builder.school ;
         this.sex = builder.sex ;
     }
	 
	//内部类构建器，利用构建器作为参数来构建Student对象
     static class StudentBuilder
     {
         String name = null ;
         int number = -1 ;
         String sex = null ;
         int age = -1 ;
         String school = null ;
         public StudentBuilder setName(String name)
         {
             this.name = name;
             return  this ;
         }

         public StudentBuilder setNumber(int number) 
         {
             this.number = number;
             return  this ;
         }

         public StudentBuilder setSex(String sex) 
         {
             this.sex = sex;
             return  this ;
         }

         public StudentBuilder setAge(int age)
         {
             this.age = age;
             return  this ;
         }

         public StudentBuilder setSchool(String school)
         {
             this.school = school;
             return  this ;
         }
         
         public Student build()
         {
             return new Student(this);
         }
         
     }//StudentBuilder
	 
}//Student