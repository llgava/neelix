package net.llgava.items;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextInventoryPageItem extends NeelixInventoryItem {

  public NextInventoryPageItem(ItemStack item, int slot) {
    super("next_page", item, slot);
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
    paginatedInventory.openInventoryOnNextPage(whoClicked);
  }
}
