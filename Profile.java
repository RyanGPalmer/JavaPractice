import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Profile {
	public static final String TOKEN_NAME = "name";
	public static final String TOKEN_PROF = "prof";
	public static final String TOKEN_BDD = "bdd";
	public static final String TOKEN_BDM = "bdm";
	public static final String TOKEN_BDY = "bdy";
	public static final String OPERATOR_ASSIGN = "=";
	public static final String EXTENSION_PROFILE = ".prf";
	public static final String MISC_DEFAULTNAME = "anon";

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

	String getFileDataString() {
		return	TOKEN_NAME + OPERATOR_ASSIGN + name + '\n' +
			TOKEN_PROF + OPERATOR_ASSIGN + prof + '\n' +
			TOKEN_BDD + OPERATOR_ASSIGN + bd.getDayValue() + '\n' +
			TOKEN_BDM + OPERATOR_ASSIGN + bd.getMonthValue() + '\n' +
			TOKEN_BDY + OPERATOR_ASSIGN + bd.getYearValue();
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
		Scanner sc = new Scanner(System.in);
		File file = new File("null.null");
		while(true) {
			System.out.println("Enter profile name:");
			String name = sc.next();
			file = new File(name + EXTENSION_PROFILE);
			if(file.exists()) break;
			else System.out.println("Profile not found.");
		}

		// We have the file, now time to parse it
		name = pullValueFromFile(TOKEN_NAME, file);
		prof = pullValueFromFile(TOKEN_PROF, file);

		int bdd = Integer.parseInt(pullValueFromFile(TOKEN_BDD, file));
		int bdm = Integer.parseInt(pullValueFromFile(TOKEN_BDM, file));
		int bdy = Integer.parseInt(pullValueFromFile(TOKEN_BDY, file));
		bd = new Birthday(bdd, bdm, bdy);
	}

	String pullValueFromFile(String token, File file) {
		String value = null;

		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				if(line.toLowerCase().contains(token)) {
					String[] splitLine = line.split(OPERATOR_ASSIGN);
					value = splitLine[1];
				}
			}
		} catch (Exception e) { }

		return value;
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
		int d = 0;
		int m = 0;
		int y = 0;
		while (true) {
			System.out.println("What is your birthday? (Format: DD MM YYYY)");
			d = sc.nextInt();
			m = sc.nextInt();
			y = sc.nextInt();
			if(Birthday.isValidDate(d,m,y)) break;
					else System.out.println("Invalid date.");
		}

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
			String filename = ((name != null) ? name : MISC_DEFAULTNAME) + EXTENSION_PROFILE;
			File file = new File(filename);
			if(file.createNewFile()) {
				System.out.println("Creating " + filename + "...");
				FileWriter wr = new FileWriter(file);
				wr.write(getFileDataString());
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
					wr.write(getFileDataString());
					wr.close();
				} else System.out.println("Profile not saved. Data will be lost when program ends.");
			}
		} catch(Exception e) { System.out.println("An error occurred."); }
	}
}
