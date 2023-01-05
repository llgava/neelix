package net.llgava.items;

import lombok.Getter;
import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextInventoryPageItem extends NeelixInventoryItem {
  @Getter private final int slot;

  public NextInventoryPageItem(int slot, org.bukkit.inventory.ItemStack item) {
    super(item);
    this.slot = slot;
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    if (inventory.isPaginatedInventory()) {
      NeelixPaginatedInventory paginatedInventory = (NeelixPaginatedInventory) inventory;
      paginatedInventory.openInventoryOnNextPage(whoClicked);
    }
  }
}
