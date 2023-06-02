package net.llgava.utils;

import org.bukkit.ChatColor;

public enum NeelixDefaultValues {
  DEFAULT_TRUE_STACK_TITLE("Enabled"),
  DEFAULT_FALSE_STACK_TITLE("Disabled");

  private final String value;

  NeelixDefaultValues(String value) {
    this.value = value;
  }

  public static NeelixDefaultValues findByIndex(int index) {
    for (NeelixDefaultValues value : NeelixDefaultValues.values()) {
      if (value.ordinal() == index) {
        return value;
      }
    }

    return null;
  }

  public String getValue() {
    return ChatColor.RESET + this.value;
  }
}
