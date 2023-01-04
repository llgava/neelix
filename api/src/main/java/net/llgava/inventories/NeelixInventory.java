package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class NeelixInventory {
  @Getter protected final int size;
  @Getter protected final String title;
  @Getter protected final List<Integer> lockedSlots;
  @Getter protected final Inventory inventory;
  @Getter protected final NeelixInventoryType type = NeelixInventoryType.DEFAULT;

  @Getter protected int currentSlot;

  public NeelixInventory(int size, String title, List<Integer> lockedSlots) {
    this.currentSlot = 0;
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  /** Avoid locked slots and add an item. */
  protected void skipLockedSlots() {
    if(this.lockedSlots.contains(this.currentSlot)) {
      do {
        this.currentSlot++;
      } while (this.lockedSlots.contains(this.currentSlot));
    }
  }

  public boolean isPaginatedInventory() {
    return this instanceof NeelixPaginatedInventory;
  }

  //public abstract  void onUse(InventoryClickEvent event, int clickedSlot, Player whoClicked);
}
