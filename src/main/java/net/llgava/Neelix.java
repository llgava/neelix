package net.llgava;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.logging.Logger;

public class Neelix extends JavaPlugin {
  public static final Logger LOGGER = Bukkit.getServer().getLogger();

  /** This constructor is used only for unit tests! */
  protected Neelix(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
    super(loader, description, dataFolder, file);
  }

  @Override
  public void onEnable() { /* Nothing to do here :) */ }
}
