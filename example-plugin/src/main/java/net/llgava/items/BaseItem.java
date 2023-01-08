package net.llgava.items;

import net.llgava.inventories.NeelixInventory;
import net.llgava.inventories.NeelixInventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BaseItem extends NeelixInventoryItem {
  public BaseItem(ItemStack item) {
    super(item);
  }

  public BaseItem(Integer slot, ItemStack item) {
    super(slot, item);
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    whoClicked.getInventory().addItem(clickedItem);
  }
}
