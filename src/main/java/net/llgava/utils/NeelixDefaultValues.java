package net.llgava.utils;

/** Default values for everything. */
public enum NeelixDefaultValues {
  NEXT_PAGE_ITEM_TITLE("&fNext page&r"),
  PREVIOUS_PAGE_ITEM_TITLE("&fPrevious page&r"),
  NEXT_PAGE_ITEM_SLOT(8),
  PREVIOUS_PAGE_ITEM_SLOT(0),
  TRUE_ITEM_TITLE("&fEnabled&r"),
  FALSE_ITEM_TITLE("&fDisabled&r");

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

  public String getString() {
    return this.value
      .replace("&", "§");
  }

  public int getInt() { return Integer.parseInt(this.value); }
}
