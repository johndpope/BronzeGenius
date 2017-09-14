package Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * @author xuch.
 */
public class StorageModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    private Person providePerson() {
        Person person = new Person();
        person.setAge(88);
        person.setName("Another Default Name");
        return person;
    }
}
