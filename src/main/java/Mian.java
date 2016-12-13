import java.math.BigInteger;
import java.util.Scanner;

public class Mian {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Integer n = 100;//Integer.valueOf(sc.nextLine());

		BigInteger sum = new BigInteger("1");
		for (int i = 1; i <= n; i++) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}

		
		System.out.println(sum);
	}

}
