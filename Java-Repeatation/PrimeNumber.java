import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number ");
		int n = scan.nextInt();
		if (!primeNumber(n)) {
			System.out.println(n + " " + "primeNumber");
		} else {
			System.out.println(n + " " + " not prime number");
		}

	}

	public static boolean primeNumber(int n) {
		boolean flag = false;

		for (int i = 2; i <= n / 2; ++i) {
			if (n % i == 0) {
				flag = true;
				break;
			}
		}

		return flag;
	}
}
