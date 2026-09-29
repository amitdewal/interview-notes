/**
 * 
 */
package com.stream.collectors.collect;

/**
 * 
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		List<Employee> employees = List.of(new Employee(1L, "Amit", "IT", 90000.0, 30, true),
				new Employee(2L, "Rahul", "HR", 60000.0, 28, true),
				new Employee(3L, "Priya", "IT", 120000.0, 35, false),
				new Employee(4L, "Neha", "Finance", 75000.0, 32, true),
				new Employee(5L, "Vikas", "HR", 55000.0, 25, false));

		// Count employees department-wise.
		Map<String, Long> count = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(count);

// ========================================================================================================		

		// Average salary department-wise.
		Map<String, Double> averageSalary = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(averageSalary);

// ========================================================================================================		

//		Total salary department-wise.
		Map<String, Double> totalSalary = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));

		System.out.println(totalSalary);
// ========================================================================================================		

//		Group employees by department. Map<String,List<Employee>>
		Map<String, List<Employee>> empByDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

//========================================================================================================		

//		Group employee names by department.
		Map<String, List<String>> nameListBYDepartment = employees.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(nameListBYDepartment);

//========================================================================================================	
//		Maximum salary per department
		Map<String, Double> collect = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(Employee::getSalary)), opt -> opt.get().getSalary())));
		System.out.println(collect);

//Minimum salary per department.
//========================================================================================================	

		Map<String, Double> collect2 = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.minBy(Comparator.comparing(Employee::getSalary)), opt -> opt.get().getSalary())));
		System.out.println(collect2);

//	========================================================================================================	
//	Count active employees department-wise.

		Map<String, Long> collect3 = employees.stream().filter(emp -> emp.isActive())
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(collect3);

		// other approach
		Map<String, Long> collect4 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.filtering(emp -> emp.isActive(), Collectors.counting())));
		System.out.println(collect4);

//	Average age department-wise.
//	========================================================================================================	
		Map<String, Double> collect5 = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)));
		System.out.println(collect5);

//  ========================================================================================================	
//        Highest salary employee per department.
//        {
//        	 IT=Employee(...),
//        	 HR=Employee(...)
//        	}
		Map<String, Optional<Employee>> collect6 = employees.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

		Map<String, String> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, 
	Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSalary)), opt -> opt.get().getName())));

		
		// 
		String input = "success";
	Map<String, Long> collect7 = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	System.out.println(collect7);
	
	Map<Integer, Long> collect8 = Arrays.stream(input.split("")).collect(Collectors.groupingBy(str->str.length(),Collectors.counting()));
	}
}
