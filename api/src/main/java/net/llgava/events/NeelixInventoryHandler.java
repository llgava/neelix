package net.llgava.events;

import net.llgava.Neelix;
import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NeelixInventoryHandler implements Listener {

  @EventHandler
  public void handler(InventoryClickEvent event) {
    Player player = (Player) event.getView().getPlayer();
    int clickedSlot = event.getRawSlot();
    NeelixInventory inventory = Neelix.getInventoryManager().getInventoryByTitle(event.getView().getTitle());

    if (inventory == null) { return; }
    if (inventory.isPaginatedInventory()) {
      NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;

      NeelixInventoryItem clickedItem = paginatedInventory.getPages().get(paginatedInventory.getCurrentOpenedPage()).get(clickedSlot);

      if (clickedItem == null) { return; }

      clickedItem.onClick(inventory, player, clickedSlot, clickedItem.getItem());
    }

    event.setCancelled(true);
  }
}
