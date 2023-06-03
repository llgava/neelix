package net.llgava.inventories.handlers;

import net.llgava.inventories.NeelixInventory;
import org.bukkit.entity.Player;

public interface OpenNeelixInventoryHandle {
  /**
   * A code block to be triggered when the NeelixInventory be opened.
   * */
  void onOpenInventory(NeelixInventory neelixInventory, Player player);
}
