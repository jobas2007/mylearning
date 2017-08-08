package com.misc;
import java.util.HashSet;

class Employee {

	String name;
	int age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee employee = (Employee) obj;
		return employee.getAge() == this.getAge() && employee.getName() == this.getName();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + age;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

}

public class TestHashCode {
	public static void main(String[] args) {
		Employee employee = new Employee("rajeev", 24);
		Employee employee1 = new Employee("rajeev", 25);
		Employee employee2 = new Employee("rajeev", 24);

		HashSet<Employee> employees = new HashSet<Employee>();
		employees.add(employee);
		System.out.println(employees.contains(employee2));
		System.out.println(
				"employee.hashCode():  " + employee.hashCode() + "  employee2.hashCode():" + employee2.hashCode());
	}
}
