package pl.edu.agh.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class Person {

    private String name;

    private String surname;

    private Integer age;

    public Person() {

    }

    public Person(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)) {
            return false;
        }
        var other = (Person)obj;
        return name.equals(other.name) &&
                surname.equals(other.surname) &&
                age.equals(other.age);
    }
}

class MyMapTest {

    private MyMap<String, Person> filledMap;

    private MyMap<String, Person> emptyMap;

    @BeforeEach
    void createMaps() {
        emptyMap = new MyMap<>();
        filledMap = new MyMap<>();
        filledMap.put("FDsdfDFH42SD", new Person("Elon", "Musk", 52));
        filledMap.put("Fkjhgfg45fDF", new Person("Jeff", "Bezos", 59));
        filledMap.put("VCXdfdsDFS34", new Person("Mark", "Zuckerberg", 39));
    }

    @Test
    void size() {

        assertEquals(0, emptyMap.size());
        assertEquals(3, filledMap.size());

        var removedVal = filledMap.remove("Fkjhgfg45fDF");

        assertEquals(2, filledMap.size());

        assertEquals(removedVal, new Person("Jeff", "Bezos", 59));
    }

    @Test
    void isEmpty() {
        assertTrue(emptyMap.isEmpty());

        assertFalse(filledMap.isEmpty());
    }

    @Test
    void containsKey() {
        assertTrue(filledMap.containsKey("FDsdfDFH42SD"));
        assertTrue(filledMap.containsKey("VCXdfdsDFS34"));
    }

    @Test
    void containsValue() {
        assertTrue(filledMap.containsValue(new Person("Elon", "Musk", 52)));
    }

    @Test
    void get() {
        var val = filledMap.get("VCXdfdsDFS34");
        assertTrue(val.equals(new Person("Mark", "Zuckerberg", 39)));
    }

    @Test
    void put() {
        filledMap.put("SDsadsa@#Sd", new Person("Bill", "Gates", 68));

        assertTrue(filledMap.containsKey("SDsadsa@#Sd"));
    }

    @Test
    void remove() {
        filledMap.remove("VCXdfdsDFS34");
        assertFalse(filledMap.containsKey("VCXdfdsDFS34"));
    }
}