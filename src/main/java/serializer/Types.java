package serializer;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public enum Types {
  BYTE("byte") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      return new byte[]{field.getByte(obj)};
    }

    @Override
    public Byte byteArrayToValue(byte[] array) {
      return array[0];
    }
  },

  SHORT("short") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(2);
      return buffer.putShort(field.getShort(obj)).array();
    }

    @Override
    public Short byteArrayToValue(byte[] array) {
      return ByteBuffer.wrap(array).getShort();
    }
  },

  INT("int") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(4);
      return buffer.putInt(field.getInt(obj)).array();
    }

    @Override
    public Integer byteArrayToValue(byte[] array) {
      return ByteBuffer.wrap(array).getInt();
    }
  },

  LONG("long") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(8);
      return buffer.putLong(field.getLong(obj)).array();
    }

    @Override
    public Long byteArrayToValue(byte[] array) {
      return ByteBuffer.wrap(array).getLong();
    }
  },

  FLOAT("float") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(4);
      buffer.putFloat(field.getFloat(obj));
      return buffer.array();
    }

    @Override
    public Float byteArrayToValue(byte[] array) {
      return ByteBuffer.wrap(array).getFloat();
    }
  },

  DOUBLE("double") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(8);
      buffer.putDouble(field.getDouble(obj));
      return buffer.array();
    }

    @Override
    public Double byteArrayToValue(byte[] array) {
      return ByteBuffer.wrap(array).getDouble();
    }
  },

  CHAR("char") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(2);
      buffer.putChar(field.getChar(obj));
      return buffer.array();
    }

    @Override
    public Character byteArrayToValue(byte[] array) {
      ByteBuffer wrapped = ByteBuffer.wrap(array);
      return wrapped.getChar();
    }
  },

  BOOLEAN("boolean") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      return new byte[]{(byte) (field.getBoolean(obj) ? 1 : 0)};
    }

    @Override
    public Boolean byteArrayToValue(byte[] array) {
      return array[0] == 1;
    }
  },
  INTEGER("integer") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(4);
      return buffer.putInt(field.getInt(obj)).array();
    }

    @Override
    public Integer byteArrayToValue(byte[] array) {
      ByteBuffer wrapped = ByteBuffer.wrap(array);
      return wrapped.getInt();
    }
  },

  CHARACTER("character") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      ByteBuffer buffer = ByteBuffer.allocate(2);
      return buffer.putChar(field.getChar(obj)).array();
    }

    @Override
    public Character byteArrayToValue(byte[] array) {
      ByteBuffer wrapped = ByteBuffer.wrap(array);
      return wrapped.getChar();
    }
  },

  STRING("string") {
    @Override
    public byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException {
      String val = (String) field.get(obj);
      return val.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String byteArrayToValue(byte[] array) {
      return new String(array, StandardCharsets.UTF_8);
    }
  };

  String type;

  Types(String type) {
    this.type = type;
  }

  abstract byte[] valueToByteArray(Field field, Object obj) throws IllegalAccessException;

  abstract <T> T byteArrayToValue(byte[] array);
}
