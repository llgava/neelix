package net.llgava;

import lombok.Getter;
import net.llgava.resources.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class Neelix extends JavaPlugin {
  @Getter private static CustomConfig exampleYAML;
  @Getter private static CustomConfig exampleJSON;

  @Override
  public void onEnable() {
    exampleYAML = new CustomConfig.YAML(this, "example.yml", true).build();
    exampleJSON = new CustomConfig.JSON(this, "example.json", true).build();
  }
}
