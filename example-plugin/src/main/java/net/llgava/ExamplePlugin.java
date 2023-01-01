package net.llgava;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {
  @Getter private static ExamplePlugin instance;

  @Override
  public void onEnable() {
    instance = this;

    Resources.Registry();
  }
}
