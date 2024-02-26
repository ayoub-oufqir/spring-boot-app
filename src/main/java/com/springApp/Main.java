package com.springApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@SpringBootApplication
@RestController
public class Main{
	// Fake Database
	private static List<Customer> customers;
	static {
		customers = new ArrayList<>();
		Customer ayoub = new Customer(
				1,
				"ayoub",
				"ayoub@gmail.com",
				23);
		customers.add(ayoub);
		Customer hamza = new Customer(
				2,
				"hamza",
				"hamza@gmail.com",
				22);
		customers.add(hamza);
		Customer mohamed = new Customer(
				3,
				"mohamed",
				"mohamed@gmail.com",
				24);
		customers.add(mohamed);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@GetMapping("api/v1/customers")
	public List<Customer> getCustomers(){
		return customers;
	}

	@GetMapping("api/v1/customers/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId){
		Customer customer = customers
				.stream()
				.filter(c -> c.id.equals(customerId))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("#### customer with id [%s] not found ####".formatted(customerId)));
		return customer;
	}
	static class Customer{
		private Integer id;
		private String name;
		private String email;
		private Integer age;

		public Customer(Integer id, String name, String email, Integer age) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.age = age;
		}

		public Customer() {
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Customer customer = (Customer) o;
			return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name, email, age);
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
					.add("id=" + id)
					.add("name='" + name + "'")
					.add("email='" + email + "'")
					.add("age=" + age)
					.toString();
		}
	}


}

