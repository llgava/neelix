package net.llgava.inventories;


import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter NeelixInventoryType type = NeelixInventoryType.SIMPLE;
  @Getter Map<Integer, NeelixInventoryItem> parsedItem = new HashMap<>();

  public NeelixSimpleInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    super(size, title, lockedSlots, items);
    this.mountInventory();
  }

  @Override
  protected void mountInventory() {
    Map<Integer, NeelixInventoryItem> items = new HashMap<>();

    for (NeelixInventoryItem inventoryItem : this.items) {
      this.skipLockedSlots();

      if (this.currentSlot > this.size - 1) { return; }

      this.parsedItem.put(this.currentSlot, inventoryItem);
      this.inventory.setItem(this.currentSlot, inventoryItem.getItem());
      this.currentSlot++;
    }
  }
}
