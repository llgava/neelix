package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class NeelixInventoryItem {
  @Getter private final Integer slot;
  @Getter private final ItemStack item;
  @Getter private final UUID identifier;

  public NeelixInventoryItem(ItemStack item) {
    this(null, item);
  }

  public NeelixInventoryItem(@Nullable Integer slot, ItemStack item) {
    this.slot = slot;
    this.item = item;
    this.identifier = UUID.randomUUID();
  }

  public Material getType() {
    return this.item.getType();
  }

  public abstract void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem);
}
