package net.llgava.utils;

import net.llgava.Neelix;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NeelixUtils {

  /**
   * The formatted version should be something like the Minecraft versioning "1.20.0".
   * @return The formatted server version.
   */
  public static String getServerVersion() {
    String version = Bukkit.getBukkitVersion();
    int i = version.indexOf("-");

    return version.substring(0, i);
  }

  /**
   * Compare the current server version with a minimum version.
   * @param minVersion The minimum Minecraft Server Version.
   * @return If the server version matches the minimum version.
   */
  public static boolean isMinimumServerVersion(String minVersion) {
    String svVersion = NeelixUtils.getServerVersion();

    String[] minVersionComponents = minVersion.split("\\.");
    String[] svVersionComponents = svVersion.split("\\.");

    int minLeng = minVersionComponents.length;
    int maxLeng = Math.max(minLeng, svVersionComponents.length);

    for (int i = 0; i < maxLeng; i++) {
      int min = (i < minLeng) ? Integer.parseInt(minVersionComponents[i]) : 0;
      int server = (i < svVersionComponents.length) ? Integer.parseInt(svVersionComponents[i]) : 0;

      if (server > min) {
        return true;
      } else if (server < min) {
        return false;
      }
    }

    return true;
  }

  /**
   * Parse the values on all occurrences. <br />
   * Keep in mind that text formatting occurs in ascending order. <br />
   * The text should be something like: "This is my example {0}"
   *
   * @param message The minimum Minecraft Server Version.
   * @param values The minimum Minecraft Server Version.
   *
   * @return The parsed message.
   */
  public static String parseMessage(String message, String... values) {
    String pattern = "\\{\\d+}";
    Pattern rgx = Pattern.compile(pattern);
    Matcher matcher = rgx.matcher(message);
    int ocrs = 0;

    while (matcher.find()) { ocrs++; }

    if (values.length == 0 || ocrs == 0) return message;

    String parsedMessage = message;
    for (int i = 0; i < ocrs; i++) {
      if (values[i].isEmpty()) {
        Neelix.LOGGER.warning(
          NeelixUtils.parseMessage(
            NeelixMessages.INVALID_PARSED_MESSAGE.getMessage(),
            String.valueOf(values.length),
            String.valueOf(ocrs)
          )
        );

        return message;
      }

      String ocr = String.format("{%d}", i);
      parsedMessage = parsedMessage
        .replace(ocr, values[i]);
    }

    return parsedMessage;
  }
}
