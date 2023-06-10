package net.llgava.events;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryManager;
import net.llgava.inventories.handlers.CloseNeelixInventoryHandle;
import net.llgava.inventories.handlers.OpenNeelixInventoryHandle;
import net.llgava.items.NeelixInventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class NeelixInventoryHandler implements Listener {
  private final NeelixInventoryManager inventoryManager;

  /**
   * <b>NEELIX'S INTERNAL USE</b> <br />
   * This listener is used internally for Neelix. <b>DO NOT</b> re-register on your plugin.
   *
   * @param inventoryManager The manager responsive to control all Neelix inventories.
   * */
  public NeelixInventoryHandler(NeelixInventoryManager inventoryManager) {
    this.inventoryManager = inventoryManager;
  }

  @EventHandler
  public void clickHandler(InventoryClickEvent event) {
    int clickedSlot = event.getRawSlot();
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.inventoryManager.getInventoryByTitle(event.getView().getTitle());
    if (inventory == null) return;

    event.setCancelled(true);
    NeelixInventoryItem clickedItem = inventory.getClickedItem(clickedSlot);
    if (clickedItem == null) return;

    clickedItem.onClick(inventory, player, clickedSlot, clickedItem.getItem());
  }

  @EventHandler
  public void openHandler(InventoryOpenEvent event) {
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.inventoryManager.getInventoryByTitle(event.getView().getTitle());
    if (inventory == null) return;

    if (!inventory.isFullHandled() && !inventory.hasOpenHandled()) return;

    ((OpenNeelixInventoryHandle) inventory).onOpenInventory(inventory, player);
  }

  @EventHandler
  public void closeHandler(InventoryCloseEvent event) {
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.inventoryManager.getInventoryByTitle(event.getView().getTitle());
    if (inventory == null) return;

    if (!inventory.isFullHandled() && !inventory.hasCloseHandled()) return;

    ((CloseNeelixInventoryHandle) inventory).onCloseInventory(inventory, player);
  }
}
