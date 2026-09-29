/**
 * 
 */
package com.stream.collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 */
public class Main {
	public static void main(String[] args) {
		List<Employee> employees = List.of(new Employee(101, "Amit", "IT", 80000),
				new Employee(102, "Rahul", "IT", 90000), new Employee(103, "Priya", "HR", 60000),
				new Employee(104, "Neha", "HR", 70000), new Employee(105, "Vikas", "Finance", 95000),
				new Employee(106, "Ankit", "Finance", 50000), new Employee(107, "Rohit", "IT", 90000),
				new Employee(108, "Sneha", "HR", 60000));

//		Basic Collectors
//		Get all employee names as List.// [Amit,Rahul,Priya,...]
		List<String> names = employees.stream().map(Employee::getName).collect(Collectors.toList());
		System.out.println(names);

//		 Get all departments as Set. //[IT,HR,Finance]
		Set<String> departments = employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
		System.out.println(departments);

//		create Map<Integer,String>  101 -> Amit hint to map toMap()
		Map<Integer, String> map = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
		System.out.println(map);

//		Get comma separated employee names.
		// Amit,Rahul,Priya... hint joining()
		String collect = employees.stream().map(Employee::getName).collect(Collectors.joining(", "));
		System.out.println(collect);

//		Count total employees.
//		hint counting()
		Long count = employees.stream().collect(Collectors.counting());
		System.out.println(count);

	}

}
