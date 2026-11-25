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
public class InvoiceClient {
	public static void main(String[] args) {
		List<Invoice> invoices = List.of(
			    new Invoice(1, 10, "Northwind",  500.0, "PAID"),
			    new Invoice(2, 10, "Northwind",  200.0, "PAID"),
			    new Invoice(3, 10, "Northwind", 1000.0, "VOID"),
			    new Invoice(4, 20, "Contoso",     900.0, "PAID"),
			    new Invoice(5, 30, "Fabrikam",    300.0, "DRAFT"),
			    new Invoice(6, 30, "Fabrikam",    150.0, "PAID"),
			    new Invoice(7, 40, "Adventure",   900.0, "PAID"),
			    new Invoice(8, 50, "Tailspin",    400.0, "PAID"),
			    new Invoice(9, 50, "Tailspin",    400.0, "PAID")
			);
		
//		An accounts team records supplier invoices as `PAID`, `DRAFT`, or `VOID`. Total only the `PAID` invoices for each supplier.
//		Sort by the total descending, and by supplier id when the totals are equal.
		
//		Contoso: 900.0
//		Adventure: 900.0
//		Tailspin: 800.0
//		Northwind: 700.0
//		Fabrikam: 150.0
		
		record InvoiceDetails(int id, String name) {};
		
		LinkedHashMap<String, Double> collect = invoices.stream().filter(Objects::nonNull).filter(invoice -> "PAID".equalsIgnoreCase(invoice.getStatus()))
		.collect(Collectors.groupingBy(invoice -> new InvoiceDetails(invoice.getSupplierId(),invoice.getSupplierName()),
		Collectors.summingDouble(Invoice::getAmount)))
		.entrySet()
		.stream()
		.sorted(Map.Entry.<InvoiceDetails,Double>comparingByValue().reversed().thenComparing(entry->entry.getKey().id()))
		.collect(Collectors.toMap(entry->entry.getKey().name(), entry->entry.getValue(),(ov,nv)->ov,LinkedHashMap::new));

		System.out.println(collect);
		
	}

}
