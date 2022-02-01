package entity;

import java.util.Objects;

public class User {
  private String name;
  private int age;
  private boolean isMarried;
  private double growth;

  public User() {
  }

  public User(String name, int age, boolean isMarried, double growth) {
    this.name = name;
    this.age = age;
    this.isMarried = isMarried;
    this.growth = growth;
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

  public boolean isMarried() {
    return isMarried;
  }

  public void setMarried(boolean married) {
    isMarried = married;
  }

  public double getGrowth() {
    return growth;
  }

  public void setGrowth(double growth) {
    this.growth = growth;
  }

  @Override
  public String toString() {
    return "User1{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", isMarried=" + isMarried +
        ", growth=" + growth +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (age != user.age) return false;
    if (isMarried != user.isMarried) return false;
    if (Double.compare(user.growth, growth) != 0) return false;
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = name != null ? name.hashCode() : 0;
    result = 31 * result + age;
    result = 31 * result + (isMarried ? 1 : 0);
    temp = Double.doubleToLongBits(growth);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
