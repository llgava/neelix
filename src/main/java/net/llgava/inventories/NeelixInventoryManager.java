package net.llgava.inventories;

import lombok.Getter;
import net.llgava.events.NeelixInventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class NeelixInventoryManager {
  @Getter private final List<NeelixInventory> inventories = new ArrayList<>();

  private final JavaPlugin pluginInstance;

  /**
   * An instance of inventories manager.
   * @param plugin The instance of the plugin.
   */
  public NeelixInventoryManager(JavaPlugin plugin) {
    this.pluginInstance = plugin;

    this.pluginInstance.getServer().getPluginManager().registerEvents(
      new NeelixInventoryHandler(this), this.pluginInstance
    );
  }

  /**
   * Register a new inventory.
   * @param inventory The inventory to be registered
   */
  public void registerInventory(NeelixInventory inventory) {
    Bukkit.getScheduler().runTaskAsynchronously(this.pluginInstance, () -> {
      this.inventories.add(inventory);
    });
  }

  /**
   * Removes an inventory by the title.
   * @param value The inventory title.
   * */
  public void removeInventoryByTitle(String value) {
    NeelixInventory inventory = this.getInventoryByTitle(value);
    this.inventories.remove(inventory);
  }

  /**
   * @param value The inventory title.
   * @return An inventory by the title.
   * */
  public NeelixInventory getInventoryByTitle(String value) {
    for (NeelixInventory inventory : this.inventories) {
      if (inventory.getTitle().equals(value)) {
        return inventory;
      }
    }

    return null;
  }
}
