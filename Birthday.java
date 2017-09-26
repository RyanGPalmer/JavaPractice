import java.util.*;
import java.time.LocalDate;

public class Birthday {
	int m;
	int d;
	int y;

	static Map<Integer, Integer> monthDays = new HashMap<Integer, Integer>();
	static {
		monthDays.put(1, 31);
		monthDays.put(2, 28);
		monthDays.put(3, 31);
		monthDays.put(4, 30);
		monthDays.put(5, 31);
		monthDays.put(6, 30);
		monthDays.put(7, 31);
		monthDays.put(8, 31);
		monthDays.put(9, 30);
		monthDays.put(10, 31);
		monthDays.put(11, 30);
		monthDays.put(12, 31);
	}

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
		// Make sure arguments are valid
		
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

	public int getDayValue() {
		return d;
	}
	
	public int getMonthValue() {
		return m;
	}

	public int getYearValue() {
		return y;
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

	public static boolean isValidDate(int d, int m, int y) {
		if(m > 12) return false;
		else if(m < 1) return false;
		if(d > monthDays.get(m)) return false;
		else if (d < 1) return false;

		// Check if date is in the future
		LocalDate date = LocalDate.now();
		if(y > date.getYear()) return false;
		else if(y == date.getYear()) {
			if(m > date.getMonthValue()) return false;
			else if(m == date.getMonthValue() && d > date.getDayOfMonth()) return false;
		}

		return true;
	}
}
