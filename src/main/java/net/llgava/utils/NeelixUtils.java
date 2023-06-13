package net.llgava.utils;

import org.bukkit.Bukkit;

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
}
