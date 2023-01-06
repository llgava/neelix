package net.llgava.utils;

import lombok.Getter;

public enum NeelixMessages {
  INVALID_FILE_EXTENSION("The file doesn't have a valid extension. Please, use {1}."),
  SUBCOMMAND_PERMISSION_CANNOT_BE_NULL("Permission cannot be null, use empty string instead."),
  INVENTORY_ITEMS_LIMIT_REACHED("The item limit for the inventory '{1}' has been reached. If you want to add more items, reduce the number of locked slots or use a paginated inventory.");

  @Getter private final String message;

  NeelixMessages(String message) {
    this.message = message;
  }

  public static NeelixMessages findByIndex(int index) {
    for (NeelixMessages message : NeelixMessages.values()) {
      if (message.ordinal() == index) {
        return message;
      }
    }

    return null;
  }
}
