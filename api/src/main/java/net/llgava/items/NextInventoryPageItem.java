package net.llgava.items;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextInventoryPageItem extends NeelixInventoryItem {

  public NextInventoryPageItem(int slot, ItemStack item) {
    super(slot, item);
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
    paginatedInventory.openInventoryOnNextPage(whoClicked);
  }
}
