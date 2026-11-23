// package main.design-pattern.creational.factory;

 interface Vehicle {
	void start();

}


 class Car implements Vehicle{

	@Override
	public void start() {
		System.out.println("Car Started");
	}

}

 class Bike implements Vehicle{

	@Override
	public void start() {
		System.out.println("Bike Started");
		
	}

}

 class VehicleFactory {

	public static Vehicle getVehicle(String type) {
		if ("CAR".equalsIgnoreCase(type)) {
			return new Car();
		}

		if ("BIKE".equalsIgnoreCase(type)) {
			return new Bike();
		}
		throw new IllegalArgumentException("Invalid vehicle");

	}

}

public class Main {
	public static void main(String[] args) {
		
		Vehicle car = VehicleFactory.getVehicle("bike");
		car.start();
	}

}
