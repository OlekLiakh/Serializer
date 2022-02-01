package serialize;

import entity.Address;
import entity.User;
import org.junit.jupiter.api.Test;
import serializer.Deserializer;
import serializer.Serializer;

import static org.junit.jupiter.api.Assertions.*;

public class TestSerializeDeserialize {

  Serializer serializer = new Serializer();

  @Test
  public void testSerializeDeserializeObject() throws IllegalAccessException, NoSuchFieldException {

    Deserializer<User> userDeserializer = new Deserializer<>();
    User userBeforeSerialization = new User("User", 15, true, 2.2);
    byte[] array = serializer.serialize(userBeforeSerialization);

    User userAfterDeserialization = userDeserializer.deserialize(array, new User());

    assertEquals(userBeforeSerialization, userAfterDeserialization);
  }

  @Test
  public void testSerializeDeserializeObjectWereFieldsHaveEqualTypes() throws IllegalAccessException, NoSuchFieldException {

    Deserializer<Address> deserializer = new Deserializer<>();
    Address addressBeforeSerialization = new Address("City", "Street", 12L);
    byte[] array = serializer.serialize(addressBeforeSerialization);

    Address addressAfterDeserialization = deserializer.deserialize(array, new Address());

    assertEquals(addressBeforeSerialization, addressAfterDeserialization);
  }
}
