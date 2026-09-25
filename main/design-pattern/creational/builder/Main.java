  class Person {

	private final String firstName;
	private final String lastName;
	private final int age;

	private Person(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + '}';
	}

	public static class Builder {
		private String firstName;
		private String lastName;
		private int age;

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;

		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Person build() {
			return new Person(this);
		}

	}
}
//  ==================================
 public class Main {

	public static void main(String[] args) {
		
		Person person = Person.builder()
				              .firstName("John")
				              .lastName("doe")
				              .age(10)
				              .build();
		System.out.println(person);
	}
}