package entity;

import java.util.Objects;

public class Address {
  private String city;
  private String street;
  private long houseNumber;

  public Address(){}

  public Address(String city, String street, Long houseNumber) {
    this.city = city;
    this.street = street;
    this.houseNumber = houseNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Long getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(Long houseNumber) {
    this.houseNumber = houseNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (houseNumber != address.houseNumber) return false;
    if (!Objects.equals(city, address.city)) return false;
    return Objects.equals(street, address.street);
  }

  @Override
  public int hashCode() {
    int result = city != null ? city.hashCode() : 0;
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + (int) (houseNumber ^ (houseNumber >>> 32));
    return result;
  }
}
