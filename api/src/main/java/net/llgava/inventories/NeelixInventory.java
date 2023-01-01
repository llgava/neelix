package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class NeelixInventory {
  @Getter protected final int size;
  @Getter protected final String title;
  @Getter protected final List<Integer> lockedSlots;
  @Getter protected Inventory inventory;

  public NeelixInventory(int size, String title, List<Integer> lockedSlots) {
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  /**
   * Avoid locked slots.
   * @param currentSlot The current slot to be skipped
   */
  protected void skipLockedSlots(int currentSlot) {
    this.skipLockedSlots(currentSlot, new ItemStack(Material.AIR));
  }

  /**
   * Avoid locked slots and add an item.
   * @param currentSlot The current slot to be skipped
   * @param item The {@link ItemStack} to be added to current slot
   */
  protected void skipLockedSlots(int currentSlot, ItemStack item) {
    if(this.lockedSlots.contains(currentSlot)) {
      do {
        this.inventory.setItem(currentSlot, item);
        currentSlot++;
      } while (this.lockedSlots.contains(currentSlot));
    }
  }

  //public abstract  void onUse(InventoryClickEvent event, int clickedSlot, Player whoClicked);
}
