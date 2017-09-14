package Guice;


import com.google.inject.Singleton;
import lombok.Data;

@Data
@Singleton
public class Person {
    private String name;
    private int age;

    public String toString() {
        return name + " of " + age + " years old";
    }
}
