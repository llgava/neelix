package net.llgava.utils;

/**
 * <b>NEELIX'S INTERNAL USE</b> <br />
 * An enum containing all standard and recommended Neelix values.
 * */
public enum NeelixDefaultValues {
  NEXT_STACK_TITLE("Next page"),
  PREVIOUS_STACK_TITLE("Previous page"),
  NEXT_STACK_SLOT(8),
  PREVIOUS_STACK_SLOT(0),
  TRUE_STACK_TITLE("Enabled"),
  FALSE_STACK_TITLE("Disabled");

  private final String value;

  NeelixDefaultValues(String value) { this.value = value; }
  NeelixDefaultValues(Integer value) { this.value = value.toString(); }

  public static NeelixDefaultValues findByIndex(int index) {
    for (NeelixDefaultValues value : NeelixDefaultValues.values()) {
      if (value.ordinal() == index) {
        return value;
      }
    }

    return null;
  }

  public String getString() { return this.value; }

  public int getInt() { return Integer.parseInt(this.value); }
}
