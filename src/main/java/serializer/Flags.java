package serializer;

import java.nio.charset.StandardCharsets;

public enum Flags {

  START_FIELD("*"),
  END_FIELD("?"),
  START_FIELD_NAME(":"),
  END_FIELD_NAME("%"),
  START_FIELD_TYPE(";"),
  END_FIELD_TYPE("$"),
  START_FIELD_DATA("~"),
  END_FIELD_DATA("#");

  String flag;

  Flags(String flag) {
    this.flag = flag;
  }

  byte [] getByteArray(){
    return flag.getBytes(StandardCharsets.UTF_8);
  }
}
