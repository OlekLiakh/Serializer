package serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Deserializer <T> {

  public T deserialize(byte[] byteArray, T instance) throws NoSuchFieldException, IllegalAccessException {

    List<byte[]> rootFields = this.getRootFields(byteArray);
    for(byte [] array: rootFields){
      writeField(instance, array);
    }

    return instance;
  }

  private void writeField(T instance, byte[] array) throws NoSuchFieldException, IllegalAccessException {
    String type = (String)this.getFieldData(array, "STRING", Flags.START_FIELD_TYPE.getByteArray(), Flags.END_FIELD_TYPE.getByteArray());
    String fieldName = (String)this.getFieldData(array, "STRING", Flags.START_FIELD_NAME.getByteArray(), Flags.END_FIELD_NAME.getByteArray());
    Object value = getFieldData(array, type, Flags.START_FIELD_DATA.getByteArray(), Flags.END_FIELD_DATA.getByteArray());

    Field field = instance.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(instance, value);
  }

  public List<byte[]> getRootFields(byte[] byteArray){
    List<byte[]> fields = new ArrayList<>();
    byte[] tempArray = new byte[0];

    for (int i = 0; i < byteArray.length; i++) {
      if (byteArray[i]==Flags.START_FIELD.getByteArray()[0]) {
        tempArray = new byte[0];
        continue;
      }
      if (byteArray[i]==Flags.END_FIELD.getByteArray()[0]) {
        fields.add(tempArray);
        continue;
      }
      tempArray = addByteToArray(tempArray, byteArray[i]);
    }
    return fields;
  }

  public Object getFieldData (byte[] byteArray, String typeStr, byte[] startFlag, byte[] endFlag){
    byte[] tempArray = new byte[0];
    Object result = null;

    for (int i = 0; i < byteArray.length; i++) {
      if (byteArray[i]==startFlag[0]) {
        tempArray = new byte[0];
        continue;
      }
      if (byteArray[i]==endFlag[0]) {
        result = Types.valueOf(typeStr).byteArrayToValue(tempArray);
        continue;
      }
      tempArray = addByteToArray(tempArray, byteArray[i]);
    }
    return result;
  }

  private byte[] addByteToArray(byte[] array, byte elem) {
    byte[] resultArray = Arrays.copyOf(array, array.length + 1);
    resultArray[resultArray.length-1] = elem;
    return resultArray;
  }
}
