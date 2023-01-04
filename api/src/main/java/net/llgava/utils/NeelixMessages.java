package net.llgava.utils;

import lombok.Getter;

public enum NeelixMessages {
  INVALID_FILE_EXTENSION("The file doesn't have a valid extension. Please, use {1}."),
  SUBCOMMAND_PERMISSION_CANNOT_BE_NULL("Permission cannot be null, use empty string instead.");

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