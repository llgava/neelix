package net.llgava;

import lombok.Getter;
import net.llgava.resources.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class Neelix extends JavaPlugin {
  @Getter private static CustomConfig exampleConfig;

  @Override
  public void onEnable() {
    exampleConfig = new CustomConfig(this, "example.yml", true).build();
  }
}
