package net.llgava.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>NEELIX'S INTERNAL USE</b> <br />
 * An enum containing all messages from Neelix.
 * */
public enum NeelixMessages {
  INVALID_FILE_EXTENSION("The file doesn't have a valid extension. Please, use '{0}' extension."),
  SUBCOMMAND_PERMISSION_CANNOT_BE_NULL("Permission cannot be null, use empty string instead."),
  INVENTORY_ITEM_SLOT_IS_LOCKED("The slot '{0}' for the item '{1}' is a locked slots. The item was ignored."),
  INVENTORY_ITEMS_LIMIT_REACHED("The item limit for the inventory '{0}' has been reached. If you want to add more items, reduce the number of locked slots or use a paginated inventory."),
  DUPLICATED_LOCKED_SLOT("TODO: '{0}' ignored!"),
  EMPTY_GENERIC_ENUM_ITEMS("The items parameter for a GenericEnumItem are empty! The initial item will be AIR.");

  private final String message;

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

  public String getParsedMessage(String... values) {
    String pattern = "\\{\\d+}";
    Pattern rgx = Pattern.compile(pattern);
    Matcher matcher = rgx.matcher(this.message);
    int ocrs = 0;

    while (matcher.find()) { ocrs++; }

    if (values.length == 0 || ocrs == 0) return this.message;

    String parsedMessage = this.message;
    for (int i = 0; i < ocrs; i++) {
      String ocr = String.format("{%d}", i);
      parsedMessage = parsedMessage
        .replace(ocr, values[i]);
    }

    return parsedMessage;
  }

  public String getRawMessage() {
    return this.message;
  }
}
