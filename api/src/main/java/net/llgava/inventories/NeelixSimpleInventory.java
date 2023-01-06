package net.llgava.inventories;


import lombok.Getter;
import net.llgava.utils.NeelixMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.llgava.Neelix.*;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter NeelixInventoryType type = NeelixInventoryType.SIMPLE;
  @Getter Map<Integer, NeelixInventoryItem> parsedItem = new HashMap<>();

  public NeelixSimpleInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    super(size, title, lockedSlots, items);
    this.mountInventory();
  }

  @Override
  protected void mountInventory() {
    for (NeelixInventoryItem inventoryItem : this.items) {
      this.skipLockedSlots();

      if (this.currentSlot > this.size - 1) {
        getNeelixLogger().warning(
          NeelixMessages.INVENTORY_ITEMS_LIMIT_REACHED.getMessage().replace("{1}", this.title)
        );
        break;
      }

      this.parsedItem.put(this.currentSlot, inventoryItem);
      this.inventory.setItem(this.currentSlot, inventoryItem.getItem());
      this.currentSlot++;
    }
  }
}
