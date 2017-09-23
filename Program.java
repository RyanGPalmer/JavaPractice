import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int age = 0;

		System.out.println("Please enter your age: ");
		age = sc.nextInt();
		System.out.println("You are " + age + " years old!");
	}
}
