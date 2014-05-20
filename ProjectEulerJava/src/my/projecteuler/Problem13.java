package my.projecteuler;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1><a href="http://projecteuler.net/problem=13">Large sum</a></h1>
 * 
 * <p>
 * Work out the first ten digits of the sum of the following one-hundred
 * 50-digit numbers.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem13 {

	public static void main(String[] args) {
		List<String> data;
		try {
			data = new Problem13().readData();
		} catch (FileNotFoundException e) {
			System.err.println("File not found " + e.getMessage());
			return;
		}
		BigInteger sum = data.stream().map(v->new BigInteger(v)).reduce((first, second)->first.add(second)).get();
		System.out.println("SUM: " + sum);
		System.out.println("Problem 13: " + sum.toString().substring(0, 10));
	}

	public List<String> readData() throws FileNotFoundException {
		List<String> result = new ArrayList<>();
		Scanner scanner = new Scanner(getClass().getResourceAsStream(
				"Problem13_data.txt"));
		while (scanner.hasNextLine()) {
			result.add(scanner.nextLine());
		}
		scanner.close();
		return result;
	}

}
