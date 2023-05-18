package net.llgava.items;

import lombok.Getter;
import lombok.Setter;
import net.llgava.inventories.NeelixInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class NeelixInventoryItem {
  @Getter @Setter private Integer slot;
  @Getter @Setter private ItemStack item;
  @Getter private final UUID uuid;

  public NeelixInventoryItem(ItemStack item) {
    this(null, item);
  }

  public NeelixInventoryItem(@Nullable Integer slot, ItemStack item) {
    this.slot = slot;
    this.item = item;
    this.uuid = UUID.randomUUID();
  }

  /**
   * Logic to be triggered when clicked at this item.
   * @param inventory The opened inventory
   * @param whoClicked A Player instance of who clicked at the item
   * @param clickedSlot The clicked slot
   * @param clickedItem The clicked item
   */
  public abstract void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem);
}
