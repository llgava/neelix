package net.llgava.inventories.handlers;

import net.llgava.inventories.NeelixInventory;
import org.bukkit.entity.Player;

public interface OpenNeelixInventoryHandle {
  /**
   * Triggered when the player close a {@link NeelixInventory}
   * @param inventory The inventory opened.
   * @param player The player who opened the inventory.
   */
  void onOpenInventory(NeelixInventory inventory, Player player);
}
