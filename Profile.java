import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Profile {
	String name;
	String prof;
	Birthday bd;

	// Default constructor asks for input from user
	public Profile() {
		Scanner sc = new Scanner(System.in);
		Character c = 'z';
		while(c != 'Y' && c != 'N') {
			System.out.println("Would you like to load an existing profile? (Y/N)");
			c = sc.next().charAt(0);
			c = Character.toUpperCase(c);
		}

		if(c == 'Y') loadExisting();
		else buildNew();
	}

	// Custom constructor allows directly defined data
	public Profile(String name, String prof, Birthday bd) {
		this.name = name;
		this.prof = prof;
		this.bd = bd;
	}

	public void buildNew() {
		name = enterName();
		prof = enterProf();
		bd = enterBD();

		// After profile is created, ask user if data should be stored in a file
		Character c = 'z';
		Scanner sc = new Scanner(System.in);
		while(c != 'Y' && c != 'N') {
			System.out.println("New profile created. Save to a file? (Y/N)");
			c = sc.next().charAt(0);
			c = Character.toUpperCase(c);
		}

		if(c == 'Y') writeToFile();
		else System.out.println("Profile not saved. Data will be lost when program ends.");
	}

	public void loadExisting() {
		// type profile name and load existing profile file
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

	void writeToFile() {
		try {
			String filename = ((name != null) ? name : "anon") + "data.prf";
			File file = new File(filename);
			if(file.createNewFile()) {
				System.out.println("Creating " + filename + "...");
				FileWriter wr = new FileWriter(file);
				wr.write(
						"name = " + name +
						"\nprof = " + prof +
						"\nbd = " + bd.getValue()
					);
				wr.close();
			} else {
				Character c = 'z';
				Scanner sc = new Scanner(System.in);
				while(c != 'Y' && c != 'N') {
					System.out.println("Profile already exists. Overwrite? (Y/N)");
					c = sc.next().charAt(0);
					c = Character.toUpperCase(c);
				}

				if(c == 'Y') {
					FileWriter wr = new FileWriter(file);
					wr.write(
							"name = " + name +
							"\nprof = " + prof +
							"\nbd = " + bd.getValue()
						);
					wr.close();
				} else System.out.println("Profile not saved. Data will be lost when program ends.");
			}
		} catch(Exception e) { System.out.println("An error occurred."); }
	}
}
