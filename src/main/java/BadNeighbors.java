import java.util.Arrays;

public class BadNeighbors {
	public static void main(String[] args) {
		int a[] = { 50, 3, 2, 15, 7, 8 };
		System.out.println(maxDonations(a));
	}

	static int[][] cache;

	public static int maxDonations(int[] donations) {
		cache = new int[2][donations.length];
		Arrays.fill(cache[0], -1);
		Arrays.fill(cache[1], -1);

		int max1 = max(donations, 2, true) + donations[0];
		int max2 = max(donations, 1, false);

		return Math.max(max2, max1);
	}

	public static int max(int[] donations, int where, boolean onegave) {
		if (where == donations.length)
			return 0;
		if (where == donations.length - 1 && onegave)
			return 0;
		else if (where == donations.length - 1)
			return donations[where];
		if (cache[onegave ? 1 : 0][where] != -1)
			return cache[onegave ? 1 : 0][where];

		int max1 = max(donations, where + 1, onegave);
		int max2 = max(donations, where + 2, onegave) + donations[where];
		return cache[onegave ? 1 : 0][where] = Math.max(max1, max2);
	}
}
