import java.util.Scanner;

public class PowerOfTwo {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number n");
		Long n = Long.parseLong(args[0]);
		// int n = scan.nextInt();
		System.out.print(poweroftwo(n));
	}
	
	private static long poweroftwo(Long n) {

		long power = 1;
		for (int i = 0; i < n; i++) {
			power = power * 2;

		}
		return power;

	}

}
