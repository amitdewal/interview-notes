/**
 * 
 */
package com.start.test.stream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 */
public class BaggageFeeClient {
	public static void main(String[] args) {
		
		record PassergerKey(int id, String name) {};
		List<BaggageFee> fees = List.of(
			    new BaggageFee(1, 11, "Ava",  25.0, "PAID"),
			    new BaggageFee(2, 11, "Ava",  15.0, "PAID"),
			    new BaggageFee(3, 11, "Ava",  40.0, "WAIVED"),
			    new BaggageFee(4, 12, "Ben",  60.0, "PAID"),
			    new BaggageFee(5, 13, "Chen", 10.0, "REFUNDED"),
			    new BaggageFee(6, 13, "Chen", 20.0, "PAID"),
			    new BaggageFee(7, 13, "Chen", 20.0, "PAID"),
			    new BaggageFee(8, 14, "Diya",  5.0, "PAID")
			);
//		An airline records baggage fees by passenger. Each fee is `PAID`, `WAIVED`, or `REFUNDED`. Total only the `PAID` fees for each passenger. 
//		Sort by the total descending, and by passenger id when the totals are equal.
		
//		Ben: 60.0
//		Ava: 40.0
//		Chen: 40.0
//		Diya: 5.0
		
		LinkedHashMap<String, Double> collect = fees.stream().filter(Objects::nonNull)
		             .filter(fee ->"PAID".equalsIgnoreCase(fee.getStatus()))
		             .filter(fee -> fee.getPassengerName() != null)
		             .collect(Collectors.groupingBy(fee->new PassergerKey(fee.getPassengerId(),fee.getPassengerName()),
		            		 Collectors.summingDouble(fee->fee.getAmount()))).entrySet()
		             .stream()
		             .sorted(Map.Entry.<PassergerKey,Double>comparingByValue().reversed().thenComparing(e->e.getKey().id()))
		             .collect(Collectors.toMap(entry->entry.getKey().name(),
		            		 Map.Entry::getValue,(a,b)->a,
		            		 LinkedHashMap::new));
		             
		System.out.println(collect);
	}

}
