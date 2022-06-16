package net.llgava.resources;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomConfig {
  private File customFile;
  @Getter private final JavaPlugin plugin;
  @Getter private final String name;
  @Getter private final boolean copyDefaults;
  @Getter private FileConfiguration config;

  public CustomConfig(JavaPlugin plugin, String name, boolean copyDefaults) {
    this.plugin = plugin;
    this.name = name;
    this.copyDefaults = copyDefaults;
  }

  public CustomConfig build() {
    File pluginFolder = this.plugin.getDataFolder();
    this.customFile = new File(pluginFolder, this.name);

    if(!pluginFolder.exists()) { pluginFolder.mkdir(); }

    if(this.copyDefaults) { this.customFile.delete(); }

    if(!this.customFile.exists()) {
      InputStream resourceFile = this.plugin.getClass().getClassLoader().getResourceAsStream(this.name);

      try {
        Files.copy(resourceFile, Paths.get(pluginFolder.getPath().concat("/").concat(this.name)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.config = YamlConfiguration.loadConfiguration(this.customFile);

    return this;
  }

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
