// package main.design-pattern.creational.prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
    		 Address address = new Address("Delhi");

	        Employee employee = new Employee(
	                "amit",
	                "engineer",
	                address
	        );

	        Employee clone = employee.clone();

	        System.out.println("Before changing clone:");

	        employee.show();
	        clone.show();

	        System.out.println(employee == clone);
	        System.out.println(employee.getAddress() == clone.getAddress());

	        // Change clone's address
			clone.getAddress().setCity("Mumbai");

	        System.out.println("\nAfter changing clone:");

	        employee.show();
	        clone.show();

	}
}

class Employee implements Cloneable {

	private String name;
	private String role;
	private Address address;

	/**
	 * @param name
	 * @param role
	 */
	public Employee(String name, String role, Address address) {
		super();
		this.name = name;
		this.role = role;
		this.address = address;
	}

	@Override
	protected Employee clone() throws CloneNotSupportedException {
		Employee clone = (Employee) super.clone();
		clone.address = this.address.clone();
		return clone;
	}

	public void show() {
		System.out.println(name + " - " + role);
		address.show();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}

class Address implements Cloneable {

	private String city;

	public Address(String city) {
		this.city = city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void show() {
		System.out.println("City: " + city);
	}

	@Override
	public Address clone() throws CloneNotSupportedException {
		return (Address) super.clone();

	}
}
