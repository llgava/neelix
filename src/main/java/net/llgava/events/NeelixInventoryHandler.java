package net.llgava.events;

import net.llgava.Neelix;
import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import net.llgava.inventories.NeelixSimpleInventory;
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
    NeelixInventoryItem clickedItem = null;

    if (inventory == null) { return; }

    if (inventory.isSimpleInventory()) {
      NeelixSimpleInventory simpleInventory = (NeelixSimpleInventory) inventory;
      clickedItem = simpleInventory.getParsedItem().get(clickedSlot);
    }

    if (inventory.isPaginatedInventory()) {
      NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
      clickedItem = paginatedInventory.getPages().get(paginatedInventory.getCurrentlyOpenPage()).get(clickedSlot);
    }

    if (clickedItem == null) { return; }

    event.setCancelled(true);

    clickedItem.onClick(inventory, player, clickedSlot, clickedItem.getItem());
  }
}
