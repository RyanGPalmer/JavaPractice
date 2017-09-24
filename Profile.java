import java.util.Scanner;

public class Profile {
	String name;
	String prof;
	Birthday bd;

	// Default constructor asks for input from user
	public Profile() {
		name = enterName();
		prof = enterProf();
		bd = enterBD();
	}
	
	// Custom constructor allows directly defined data
	public Profile(String name, String prof, Birthday bd) {
		this.name = name;
		this.prof = prof;
		this.bd = bd;
	}

	String enterName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your name?");
		return sc.next();
	}

	String enterProf() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your profession?");
		return sc.next();
	}

	Birthday enterBD() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your birthday? (Format: DD MM YYYY)");
		int d = sc.nextInt();
		int m = sc.nextInt();
		int y = sc.nextInt();
		return new Birthday(d, m, y);
	}

	public void getBlurb() {
		System.out.println(
				"Name: " + name +
				"\nProfession: " + prof +
				"\nBirthday: " + bd.getString() +
				"\nAge: " + bd.getAge()
				);
	}
}
