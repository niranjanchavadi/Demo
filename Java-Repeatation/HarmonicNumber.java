import java.util.Scanner;

public class HarmonicNumber {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number n");
		// double n = Double.parseDouble(args[0]);
		int n = scan.nextInt();
		System.out.print(harmonic(n));
	}

	private static double harmonic(double n) {

		double harmonic = 1;
		for (int i = 2; i <= n; i++) {
			harmonic += (double) 1 / i;

		}
		return harmonic;

	}

}
