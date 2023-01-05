package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class NeelixInventoryItem {
  @Getter private final UUID identifier;
  @Getter private final ItemStack item;

  public NeelixInventoryItem(ItemStack item) {
    this.item = item;
    this.identifier = UUID.randomUUID();
  }

  public abstract void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem);
}
