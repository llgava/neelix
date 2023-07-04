package net.llgava.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomYamlConfig extends CustomConfig {
  private static final String FILE_EXTENSION = ".yml";

  @Getter private FileConfiguration config;

  /**
   * Create a new configuration file in .yml format.
   * @param plugin The main class of the plugin.
   * @param name The name of the config file. (Should be the same name as the file in src/main/resources)
   * @param copyDefaults If true, all values in the default config file will be copied every time when the server is started.
   */
  public CustomYamlConfig(JavaPlugin plugin, String name, boolean copyDefaults) {
    super(plugin, name, copyDefaults);
  }

  @SneakyThrows
  @Override
  public CustomYamlConfig build() {
    if(!this.isValidExtension(FILE_EXTENSION)) return null;

    this.customFile = new File(this.dataFolder, this.name);

    if(!this.dataFolder.exists()) { this.dataFolder.mkdir(); }

    if(this.copyDefaults) { this.customFile.delete(); }

    if(!this.customFile.exists()) {
      InputStream resourceFile = this.plugin.getClass().getClassLoader().getResourceAsStream(this.name);
      Files.copy(resourceFile, Paths.get(this.dataFolder.getPath().concat("/").concat(this.name)));
    }

    this.config = YamlConfiguration.loadConfiguration(this.customFile);

    return this;
  }

  @Override
  public boolean save() {
    try {
      this.config.save(this.customFile);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
