package net.llgava;

import lombok.Getter;
import net.llgava.resources.CustomYamlConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static net.llgava.ExamplePlugin.getInstance;

public class Resources {
  private static final JavaPlugin plugin = getInstance();

  @Getter private static CustomYamlConfig customConfig;
  @Getter private static final FileConfiguration config = getInstance().getConfig();

  public static void Registry() {
    plugin.getConfig().options().copyDefaults(true);
    plugin.saveConfig();

    customConfig = new CustomYamlConfig(plugin, "custom_config.yml", true).build();
  }
}
