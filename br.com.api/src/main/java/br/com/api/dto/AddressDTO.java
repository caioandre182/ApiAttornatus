package br.com.api.dto;

import br.com.api.entities.Address;
import br.com.api.entities.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDTO {

    private Long id;

    @NotBlank(message = "field not found")
    @Size(min = 3, max = 200, message = "Street name must be between 3 or 200 characters")
    private String street;

    @NotBlank(message = "field not found")
    @Size(min = 3, max = 10, message = "Postal code must be between 3 or 10 characters")
    private String postalCode;

    @NotBlank(message = "field not found")
    @Min(value = 0)
    private Integer number;
    private String city;

    @NotBlank(message = "field not found")
    @Size(min = 3, max = 100, message = "City must be between 3 or 100 characters")
    private Integer status;
    private Person person;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {

        this.id = address.getId();
        this.street = address.getStreet();
        this.postalCode = address.getPostalCode();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.status = address.getStatus().getCode();
        this.person = address.getPerson();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
