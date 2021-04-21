package dk.oertel;

import java.util.List;
import java.util.Map;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Map<String, Object> address;
    private List<Map<String, Object>> phoneNumbers;

    public Person(String firstName, String lastName, int age, Map<String, Object> address, List<Map<String, Object>> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
