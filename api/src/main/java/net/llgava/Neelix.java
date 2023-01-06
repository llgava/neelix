package net.llgava;

import lombok.Getter;
import net.llgava.events.NeelixInventoryHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Neelix extends JavaPlugin {
  private static JavaPlugin pluginInstance;
  @Getter private static NeelixInventoryManager inventoryManager;
  @Getter private static Logger neelixLogger;

  @Override
  public void onEnable() { /* Nothing to do here :) */ }

  /**
   * An alias for {@link Neelix#initialize(JavaPlugin plugin)} method.
   * @param plugin Plugin to register
   */
  public static void init(JavaPlugin plugin) { initialize(plugin); }

  public static void initialize(JavaPlugin plugin) {
    pluginInstance = plugin;
    neelixLogger = pluginInstance.getLogger();
    inventoryManager = new NeelixInventoryManager(pluginInstance);

    registerEvents();
  }

  private static void registerEvents() {
    pluginInstance.getServer().getPluginManager().registerEvents(new NeelixInventoryHandler(), pluginInstance);
  }
}
