import java.time.LocalDate;

public class Profile {
	int age;
	String name;
	String prof;

	public Profile(String name, String prof, int age) {
		this.name = name;
		this.prof = prof;
		this.age = age;
	}

	public void getAge() {
		LocalDate date = LocalDate.now();
		System.out.println(date.getMonth());
	}
}
