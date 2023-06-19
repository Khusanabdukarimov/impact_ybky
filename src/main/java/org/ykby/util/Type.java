package org.ykby.util;

import java.util.stream.Stream;

public enum Type {
  CONFERENCE("conference"),
  TEAM("team"),
  FOCUS("focus");

  private final String label;
  Type(String label) {
    this.label = label;
  }

  public static Type fromString(String label) {
    return Stream.of(Type.values())
        .filter(e -> e.label.equals(label))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }


}
