package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class NeelixInventory {
  @Getter protected final int size;
  @Getter protected final String title;
  @Getter protected List<Integer> lockedSlots;
  @Getter protected final List<NeelixInventoryItem> items;
  @Getter protected final Inventory inventory;
  @Getter protected final NeelixInventoryType type = NeelixInventoryType.NONE;

  protected int currentSlot;

  public NeelixInventory(int size, String title, @Nullable List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    this.currentSlot = 0;
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots != null ? lockedSlots : new ArrayList<>();
    this.items = items;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  protected abstract void mountInventory();

  /** Avoid locked slots. */
  protected void skipLockedSlots() {
    if(this.lockedSlots.contains(this.currentSlot)) {
      do {
        this.currentSlot++;
      } while (this.lockedSlots.contains(this.currentSlot));
    }
  }

  public boolean isSimpleInventory() { return this instanceof NeelixSimpleInventory; }
  public boolean isPaginatedInventory() {
    return this instanceof NeelixPaginatedInventory;
  }
}
