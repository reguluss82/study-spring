package DI03;

public class Student {
	private String name;
	private int    age;
	private String gradeNum;
	private String classNum;
	//Constructor 가 setter보다 먼저 실행됨. (생성자 없이는 setter가 만들어질수 없으므로)
	public void setName(String name) {
		this.name = name;
		System.out.println("====Student setName 시작====");
	}
	public Student(String name , int age , String gradeNum , String classNum) {
		this.name     = name;
		this.age      = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
		System.out.println("====Student Construct 시작====");
	}

	public String getName() {
		return name;
	}


	public void setAge(int age) {
		this.age = age;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public int getAge() {
		return age;
	}

	public String getGradeNum() {
		return gradeNum;
	}

	public String getClassNum() {
		return classNum;
	}
	
}
