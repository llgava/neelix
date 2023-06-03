package net.llgava.items;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviousInventoryPageItem extends NeelixInventoryItem {

  /**
   * Create a full functional "previous page" inventory item. <br />
   *
   * @param item The item to be used as next item.
   * @param slot The inventory slot the item should be in. This slot will be added to the inventory {@link NeelixInventory#lockedSlots}.
   */
  public PreviousInventoryPageItem(ItemStack item, int slot) {
    super("previous_page", item, slot);
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
    paginatedInventory.openInventoryOnPreviousPage(whoClicked);
  }
}
