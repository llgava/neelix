package net.llgava.config;

import lombok.Getter;
import net.llgava.utils.NeelixMessages;
import net.llgava.utils.NeelixUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class CustomConfig {
  protected File customFile;
  @Getter protected JavaPlugin plugin;
  @Getter protected String name;
  @Getter protected boolean copyDefaults;
  protected File dataFolder;

  /**
   * Creates a new configuration file.
   * @param plugin The main class of the plugin.
   * @param name The name of the config file. (Should be the same name as the file in src/main/resources)
   * @param copyDefaults If true, all values in the default config file will be copied every time when the server is started.
   */
  public CustomConfig(JavaPlugin plugin, String name, boolean copyDefaults) {
    this.plugin = plugin;
    this.name = name;
    this.copyDefaults = copyDefaults;
    this.dataFolder = plugin.getDataFolder();
  }

  /** Create a new configuration file in .yml format. */
  public static class YAML extends CustomYamlConfig {
    public YAML(JavaPlugin plugin, String name, boolean copyDefaults) {
      super(plugin, name, copyDefaults);
    }
  }

  /** Create a new configuration file in .json format. */
  public static class JSON extends CustomJsonConfig {
    public JSON(JavaPlugin plugin, String name, boolean copyDefaults) {
      super(plugin, name, copyDefaults);
    }
  }

  /**
   * Build the custom file.
   * @return The built custom file.
   */
  public abstract CustomConfig build();

  /**
   * Automatically save any changes to the file.
   * @return True if the file was saved.
   */
  public abstract boolean save();

  /**
   * Check if the file is a valid extension.
   * @param extension The file extension.
   * @return True if is a valid extension.
   */
  protected boolean isValidExtension(String extension) {
    if(!this.name.endsWith(extension)) {
      throw new Error(
        NeelixUtils.parseMessage(
          NeelixMessages.INVALID_FILE_EXTENSION.getMessage(),
          extension
        )
      );
    }

    return true;
  }
}
