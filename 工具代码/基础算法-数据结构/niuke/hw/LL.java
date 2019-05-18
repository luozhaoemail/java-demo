package view.niuke.hw;

import java.util.Scanner;

public class LL {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] par = input.split(" ");
		int count = Integer.valueOf(par[0]);
		float sale = Float.valueOf(par[1]);

		float s1 = getRes1(count, sale);
		float s2 = getRes2(count, sale);

		if (s1 == s2) {
			System.out.println(0);
		} else if (s1 > s2) {
			System.out.println(1);
		} else {
			System.out.println(2);
		}
	}

	private static float getRes1(int count, float sale) {

		int cou = count;
		float sa = sale;

		float sum = cou * sa;
		if (cou >= 3) {
			sum *= (0.7);
		}

		if (sum >= 50) {
			sum -= 10;
		}
		
		String tmp = String.format("%.2f", sum);
		return Float.parseFloat(tmp);
	}

	private static float getRes2(int count, float sale) {

		int cou = count;
		float sa = sale;
		float sum = cou * sa;
		if (sum >= 10) {
			sum -= 2;
		}
		if (sum >= 99) {
			sum -= 6;
		}

		String tmp = String.format("%.2f", sum);
		return Float.parseFloat(tmp);
	}

}