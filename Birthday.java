import java.util.*;
import java.time.LocalDate;

public class Birthday {
	int m;
	int d;
	int y;

	static Map<Integer, String> months = new HashMap<Integer, String>();
	static {
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
	}

	public Birthday(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}

	public String getString() {
		return (months.get(m) + " " + d + ", " + y);
	}

	public String getValue() {
		return "DD MM YYYY";
	}

	public int getAge() {
		LocalDate date = LocalDate.now();
		int age = date.getYear() - y;

		// Check if this year's birthday hasn't happened yet
		if(date.getMonthValue() < m) {
			age--;
		} else if(date.getMonthValue() == m && date.getDayOfMonth() < d) {
			age--;
		}

		return age;
	}
}
