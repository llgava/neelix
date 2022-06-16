package net.llgava.resources;

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
  @Getter private FileConfiguration config;

  public CustomYamlConfig(JavaPlugin plugin, String name, boolean copyDefaults) {
    super(plugin, name, copyDefaults);
  }

  @SneakyThrows
  @Override
  public CustomYamlConfig build() {
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
