package pojo;

public class People {
	private Teacher teacher;

	public People() {
		
	}
	
	public People(Teacher teacher) {
		super();
		this.teacher = teacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "People [teacher=" + teacher + "]";
	}
		
	
}


class Teacher{
	private String name="李老师";

	@Override
	public String toString() {
		return "Teacher [name=" + name + "]";
	}
		
}