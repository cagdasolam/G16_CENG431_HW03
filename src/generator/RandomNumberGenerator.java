package generator;

import java.util.Random;

public class RandomNumberGenerator {

	public static int generateRandomNumber(int n, int k) {
		return new Random().nextInt(k - n + 1) + n;
	}

	public static int generateRandomNumber(int n) {
		return new Random().nextInt(n) + 1;
	}

}