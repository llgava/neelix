package net.llgava.inventories;

import lombok.Getter;
import net.llgava.Neelix;
import net.llgava.listener.NeelixInventoryHandlerListener;
import net.llgava.utils.NeelixMessages;
import net.llgava.utils.NeelixUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class NeelixInventoryManager {
  @Getter private final List<NeelixInventory> inventories = new ArrayList<>();

  public NeelixInventoryManager(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(
      new NeelixInventoryHandlerListener(this),
      plugin
    );
  }

  /**
   * Returns a Neelix inventory by the title.
   * @param title The inventory title.
   * @return The inventory.
   */
  public NeelixInventory getInventory(String title) {
    for (NeelixInventory inventory : this.inventories) {
      if (inventory.getTitle().equals(title)) {
        return inventory;
      }
    }

    return null;
  }

  /**
   * Add new Neelix inventory.
   * @param inventory The new Neelix inventory.
   */
  public void addInventory(NeelixInventory inventory) {
    this.addInventory(inventory, false);
  }

  /**
   * Add new Neelix inventory.
   * @param inventory The new Neelix inventory.
   * @param force If true, the inventory will overwrite other inventories with the same name.
   */
  public void addInventory(NeelixInventory inventory, boolean force) {
    if (this.inventoryExists(inventory.getTitle())) {
      Neelix.LOGGER.warning(
        NeelixUtils.parseMessage(
          NeelixMessages.INVENTORY_ALREADY_EXISTS.getMessage(),
          inventory.getTitle()
        )
      );

      if (!force) return;

      this.removeInventory(inventory.getTitle());
      this.inventories.add(inventory);
      return;
    }

    this.inventories.add(inventory);
  }

  /**
   * Remove a Neelix inventory.
   * @param title The inventory title.
   */
  public void removeInventory(String title) {
    if (!this.inventoryExists(title)) return;
    NeelixInventory inventory = this.getInventory(title);
    this.inventories.remove(inventory);
  }

  /**
   * Check if an inventory with specific title is registered.
   * @param title The inventory title.
   * @return True if the inventory exists.
   */
  public boolean inventoryExists(String title) {
    if (this.getInventory(title) == null) {
      Neelix.LOGGER.warning(
        NeelixUtils.parseMessage(
          NeelixMessages.INVENTORY_DOES_NOT_EXIST.getMessage(),
          title
        )
      );

      return false;
    }

    return true;
  }
}
