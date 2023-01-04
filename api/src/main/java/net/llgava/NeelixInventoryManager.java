package net.llgava;

import lombok.Getter;
import net.llgava.inventories.NeelixInventory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class NeelixInventoryManager {
  @Getter private final List<NeelixInventory> inventories = new ArrayList<>();

  private final JavaPlugin pluginInstance;

  public NeelixInventoryManager(JavaPlugin plugin) {
    this.pluginInstance = plugin;
  }

  public void registerInventory(NeelixInventory inventory) {
    Bukkit.getScheduler().runTaskAsynchronously(this.pluginInstance, () -> {
      this.inventories.add(inventory);
    });
  }

  public NeelixInventory getInventoryByTitle(String value) {
    for (NeelixInventory inventory : this.inventories) {
      if (inventory.getTitle().equals(value)) {
        return inventory;
      }
    }

    return null;
  }
}
