package serializer;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Serializer {

  public byte[] serialize(Object obj) throws IllegalAccessException {

    Field[] fields = obj.getClass().getDeclaredFields();
    byte[] objByteArray = {};
    for (Field field : fields) {
      objByteArray = concatArrays(objByteArray, this.getArrayOfFieldValues(field, obj));
    }
    return objByteArray;
  }

  public byte[] getArrayOfFieldValues(Field field, Object obj) throws IllegalAccessException {
    String fieldType;
    byte[] tempArray;
    field.setAccessible(true);
    tempArray = concatArrays(Flags.START_FIELD.getByteArray(), Flags.START_FIELD_NAME.getByteArray());
    tempArray = concatArrays(tempArray, field.getName().getBytes(StandardCharsets.UTF_8));
    tempArray = concatArrays(tempArray, Flags.END_FIELD_NAME.getByteArray());

    tempArray = concatArrays(tempArray, Flags.START_FIELD_TYPE.getByteArray());
    fieldType = field.getType().getSimpleName().toUpperCase(Locale.ROOT);
    tempArray = concatArrays(tempArray, fieldType.getBytes(StandardCharsets.UTF_8));
    tempArray = concatArrays(tempArray, Flags.END_FIELD_TYPE.getByteArray());

    tempArray = concatArrays(tempArray, Flags.START_FIELD_DATA.getByteArray());
    tempArray = concatArrays(tempArray, Types.valueOf(fieldType).valueToByteArray(field, obj));
    tempArray = concatArrays(tempArray, Flags.END_FIELD_DATA.getByteArray());

    tempArray = concatArrays(tempArray, Flags.END_FIELD.getByteArray());
    return tempArray;
  }

  private byte[] concatArrays(byte[] arrayOne, byte[] arrayTwo) {
    byte[] resultArray = new byte[arrayOne.length + arrayTwo.length];
    System.arraycopy(arrayOne, 0, resultArray, 0, arrayOne.length);
    System.arraycopy(arrayTwo, 0, resultArray, arrayOne.length, arrayTwo.length);
    return resultArray;
  }
}
