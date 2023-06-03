package net.llgava.inventories.handlers;

import net.llgava.inventories.NeelixInventory;
import org.bukkit.entity.Player;

public interface CloseNeelixInventoryHandle {

  /**
   * A code block to be triggered when the NeelixInventory be closed.
   * */
  void onCloseInventory(NeelixInventory neelixInventory, Player player);
}
