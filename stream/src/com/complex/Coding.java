/**
 * 
 */
package com.start.test.stream;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 */
public class Coding {
	
	public static void main(String[] args) {
		List<Loan> loans = List.of(new Loan(1, 201, "Alice", 40.0, "RETURNED"),
				new Loan(2, 202, "Bob", 80.0, "RETURNED"), new Loan(3, 201, "Alice", 10.0, "RETURNED"),
				new Loan(4, 202, "Bob", 20.0, "CANCELLED"), new Loan(5, 203, "Cara", 30.0, "PENDING"),
				new Loan(6, 203, "Cara", 90.0, "RETURNED"), new Loan(7, 203, "Cara", 20.0, "RETURNED"),
				new Loan(8, 204, "Dan", 15.0, "RETURNED"));

//		Cara: 110.0
//		Bob: 80.0
//		Alice: 50.0
//		Dan: 15.0

//		Total the fees for each member, using only `RETURNED` loans. Sort by the total descending.

		LinkedHashMap<String, Double> result = loans.stream().filter(Objects::nonNull)
				.filter(loan -> "RETURNED".equalsIgnoreCase(loan.getStatus()))
				.filter(loan -> loan.getMemberName() != null)
				.collect(Collectors.groupingBy(loan -> loan.getMemberName(),
						Collectors.summingDouble(loan -> loan.getFee())))
				.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (ov, nv) -> nv, LinkedHashMap::new));
		System.out.println(result);
	}

}
