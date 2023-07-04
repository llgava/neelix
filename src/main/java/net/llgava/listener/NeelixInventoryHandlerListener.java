package net.llgava.listener;

import net.llgava.inventories.NeelixInventoryManager;
import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.handlers.CloseNeelixInventoryHandle;
import net.llgava.inventories.handlers.OpenNeelixInventoryHandle;
import net.llgava.items.NeelixInventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class NeelixInventoryHandlerListener implements Listener {
  private final NeelixInventoryManager manager;

  /**
   * Responsible for controlling changes related to the {@link NeelixInventory}
   * @param inventoryManager The inventory manager.
   * */
  public NeelixInventoryHandlerListener(NeelixInventoryManager inventoryManager) {
    this.manager = inventoryManager;
  }

  @EventHandler
  public void onClickNeelixInventory(InventoryClickEvent event) {
    int clickedSlot = event.getRawSlot();
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.manager.getInventory(event.getView().getTitle());
    if (inventory == null) return;

    event.setCancelled(true);
    NeelixInventoryItem clickedItem = inventory.getClickedItem(clickedSlot);
    if (clickedItem == null) return;

    clickedItem.onClick(inventory, player, clickedSlot, clickedItem.getItem());
  }

  @EventHandler
  public void onOpenNeelixInventory(InventoryOpenEvent event) {
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.manager.getInventory(event.getView().getTitle());
    if (inventory == null) return;

    if (!inventory.isFullHandled() && !inventory.isOpenHandled()) return;

    ((OpenNeelixInventoryHandle) inventory).onOpenInventory(inventory, player);
  }

  @EventHandler
  public void onCloseNeelixInventory(InventoryCloseEvent event) {
    Player player = (Player) event.getView().getPlayer();
    NeelixInventory inventory = this.manager.getInventory(event.getView().getTitle());
    if (inventory == null) return;

    if (!inventory.isFullHandled() && !inventory.isCloseHandled()) return;

    ((CloseNeelixInventoryHandle) inventory).onCloseInventory(inventory, player);
  }
}
