package net.llgava.items;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviousInventoryPageItem extends NeelixInventoryItem {

  public PreviousInventoryPageItem(int slot, ItemStack item) {
    super(slot, item);
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
    paginatedInventory.openInventoryOnPreviousPage(whoClicked);
  }
}
