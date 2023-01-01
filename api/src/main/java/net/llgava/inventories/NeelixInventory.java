package net.llgava.inventories;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class NeelixInventory {
  @Getter protected final int size;
  @Getter protected final String title;
  @Getter protected final List<Integer> lockedSlots;
  @Getter @Setter protected Inventory inventory;

  @Getter protected int _currentSlot;

  public NeelixInventory(int size, String title, List<Integer> lockedSlots) {
    this._currentSlot = 0;
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  /** Avoid locked slots and add an item. */
  protected void skipLockedSlots() {
    if(this.lockedSlots.contains(this._currentSlot)) {
      do {
        this._currentSlot++;
      } while (this.lockedSlots.contains(this._currentSlot));
    }
  }

  //public abstract  void onUse(InventoryClickEvent event, int clickedSlot, Player whoClicked);
}
