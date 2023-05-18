package net.llgava.events;

import net.llgava.inventories.*;
import net.llgava.items.NeelixInventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NeelixInventoryHandler implements Listener {
  private final NeelixInventoryManager inventoryManager;

  public NeelixInventoryHandler(NeelixInventoryManager inventoryManager) {
    this.inventoryManager = inventoryManager;
  }

  @EventHandler
  public void handler(InventoryClickEvent event) {
    Player player = (Player) event.getView().getPlayer();
    int clickedSlot = event.getRawSlot();
    NeelixInventory inventory = this.inventoryManager.getInventoryByTitle(event.getView().getTitle());

    if (inventory == null) { return; }

    NeelixInventoryItem clickedItem = inventory.getClickedItem(clickedSlot);

    if (clickedItem == null) { return; }

    event.setCancelled(true);

    clickedItem.onClick(inventory, player, clickedSlot, clickedItem.getItem());
  }
}
