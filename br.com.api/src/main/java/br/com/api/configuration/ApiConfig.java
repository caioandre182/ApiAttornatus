package br.com.api.configuration;


import br.com.api.entities.Address;
import br.com.api.entities.Person;
import br.com.api.entities.enums.AddressStatus;
import br.com.api.repositories.AddressRepository;
import br.com.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class ApiConfig implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        LocalDate date = LocalDate.parse("1950-08-23");
        LocalDate date1 = LocalDate.parse("1998-01-10");

        Person p = new Person(null, "Fulano", date);
        Person p1 = new Person(null, "Ciclano", date1);

        Address a1 = new Address(null, "Rua 1", "0230230", 180, "City 1", AddressStatus.PRINCIPAL, p);
        Address a2 = new Address(null, "Rua 2", "5436545", 45, "City 2", AddressStatus.PRINCIPAL, p1);
        Address a3 = new Address(null, "Rua 3", "2132142", 67, "City 2", AddressStatus.SECONDARY, p);

        personRepository.saveAll(Arrays.asList(p, p1));
        addressRepository.saveAll(Arrays.asList(a1, a2, a3));
    }
}
