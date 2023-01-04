package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class NeelixNavigationItem {
  @Getter private final int slot;
  @Getter private final ItemStack item;

  public NeelixNavigationItem(int slot, ItemStack item) {
    this.slot = slot;
    this.item = item;
  }
}
