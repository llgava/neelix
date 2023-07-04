package net.llgava.inventories.handlers;

import net.llgava.inventories.NeelixInventory;
import org.bukkit.entity.Player;

public interface CloseNeelixInventoryHandle {
  /**
   * Triggered when the player close a {@link NeelixInventory}
   * @param inventory The inventory closed.
   * @param player The player who closed the inventory.
   */
  void onCloseInventory(NeelixInventory inventory, Player player);
}
