package net.llgava.resources;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
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

  public static class YAML extends CustomYamlConfig {
    public YAML(JavaPlugin plugin, String name, boolean copyDefaults) {
      super(plugin, name, copyDefaults);
    }
  }

  public static class JSON extends CustomJsonConfig {
    public JSON(JavaPlugin plugin, String name, boolean copyDefaults) {
      super(plugin, name, copyDefaults);
    }
  }

  /** @return The builded config file. */
  public abstract CustomConfig build();

  /** @return True if the file was saved. */
  public abstract boolean save();
}
