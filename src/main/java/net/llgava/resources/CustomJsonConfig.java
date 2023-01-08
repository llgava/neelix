package net.llgava.resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomJsonConfig extends CustomConfig {
  private static final String FILE_EXTENSION = ".json";

  @Getter private JsonObject data;

  public CustomJsonConfig(JavaPlugin plugin, String name, boolean copyDefaults) {
    super(plugin, name, copyDefaults);
  }

  @SneakyThrows
  @Override
  public CustomJsonConfig build() {
    if(!this.isValidExtension(FILE_EXTENSION)) return null;

    Gson gson = new Gson();
    this.customFile = new File(this.dataFolder, this.name);

    if(!this.dataFolder.exists()) { this.dataFolder.mkdir(); }

    if(this.copyDefaults) { this.customFile.delete(); }

    if(!this.customFile.exists()) {
      InputStream resourceFile = this.plugin.getClass().getClassLoader().getResourceAsStream(this.name);
      Files.copy(resourceFile, Paths.get(this.dataFolder.getPath().concat("/").concat(this.name)));
    }

    this.data = gson.fromJson(new FileReader(this.customFile), JsonObject.class);

    return this;
  }

  @Override
  public boolean save() {
    return false;
  }
}
