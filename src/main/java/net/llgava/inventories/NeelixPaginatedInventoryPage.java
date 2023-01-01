package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class NeelixPaginatedInventoryPage {
  @Getter private final int id;
  @Getter private final Map<Integer, ItemStack> content;

  public NeelixPaginatedInventoryPage(int id, Map<Integer, ItemStack> content) {
    this.id = id;
    this.content = content;
  }
}
