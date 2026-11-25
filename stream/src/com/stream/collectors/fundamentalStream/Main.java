/**
 * 
 */
package com.start.test.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 */
public class Main {

	public static void main(String[] args) {
//		Write a program to print the count of each character in a String?
		String s = "string data to count each character";

//		String[] arr = s.split("");
//		Map<String, Long> result = Stream.of(arr).map(String::toLowerCase)
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		System.out.println(result);

		Map<Character, Long> result = s.chars().mapToObj(c -> (char) Character.toLowerCase(c))
				.filter(Character::isLetter).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result);

//  Write a Program to find the Maximum element in an array
		int[] arr = { 12, 19, 20, 88, 00, 9 };
		int max = Arrays.stream(arr).max().getAsInt();
		System.out.println(max);

//	   How to find only duplicate elements with its count from the String ArrayList in Java8?
		List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
		Map<String, Long> result2 = names.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1)
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

		System.out.println(result2);
//		Write a Java 8 program to sort an array and then convert the sorted array into Stream?
		int arr1[] = { 99, 55, 203, 99, 4, 91 };

//		 Java 8 program to perform cube on list elements and filter numbers greater than 50.
		List<Integer> integerList = Arrays.asList(4, 5, 6, 7, 1, 2, 3);

		List<Double> collect = integerList.stream().map(num -> Math.pow(num, 3)).filter(num -> num > 50)
				.collect(Collectors.toList());
		System.out.println(collect);

//		Given a list of integers, sort all the values present in it in descending order using Stream functions?
		List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);

		List<Integer> collect2 = myList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(collect2);

//		Given a list of integers, sort all the values present in it using Stream functions?

		List<Integer> collect3 = myList.stream().sorted().collect(Collectors.toList());
		System.out.println(collect3);
//		Given a String, find the first repeated character in it using Stream functions?
		String input = "Java Articles are Awesome";

		Character character = input.chars().mapToObj(c -> (char) Character.toLowerCase(c))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).findFirst()
				.get();
		System.out.println(character);
		System.out.println("end");

//		Given a String, find the first non-repeated character in it using Stream functions?

		Character character2 = input.chars().mapToObj(c -> (char) Character.toLowerCase(c))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).findFirst()
				.get();
		System.out.println(character2);

//		Given a list of integers, find the maximum value element present in it using Stream functions?

		List<Integer> myList1 = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);

		Integer max1 = myList1.stream().max(Integer::compare).get();
		System.out.println(max1);
		Integer max2 = myList1.stream().max((a, b) -> a - b).get();
		System.out.println(max2);

//		How to find duplicate elements in a given integers list in java using Stream functions?
		List<Integer> findUplicate = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);

		HashSet<Integer> seen = new HashSet<Integer>();

		List<Integer> collect4 = findUplicate.stream().filter(n -> !seen.add(n)).collect(Collectors.toList());
		System.out.println(collect4);

//		Given a list of integers, find out all the numbers starting with 1 using Stream functions?
		List<Integer> myList3 = Arrays.asList(10, 15, 8, 49, 25, 98, 32);

		List<String> collect5 = myList3.stream().map(n -> Integer.toString(n)).filter(n -> n.startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(collect5);

		// FIND EVEN
		List<Integer> even = myList3.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
		System.out.println(even);
		List<Integer> odd = myList3.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
		System.out.println(odd);
	}

}
