package net.llgava.inventories;


import lombok.Getter;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixInventoryType;
import net.llgava.utils.NeelixMessages;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter private final NeelixInventoryType type = NeelixInventoryType.SIMPLE;

  public NeelixSimpleInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    super(size, title, lockedSlots, items);
    this.mount();
  }

  @Override
  protected void mount() {
    for (NeelixInventoryItem item : this.items) {

      // Inventory items limit
      if (this.currentSlot > this.size - 1) {
        Bukkit.getServer().getLogger().warning(
          NeelixMessages.INVENTORY_ITEMS_LIMIT_REACHED.getMessage()
            .replace("{1}", this.title)
        );

        break;
      }

      // Item with non-null slot value
      if (item.getSlot() != null) {
        if (this.lockedSlots.contains(item.getSlot())) {
          Bukkit.getServer().getLogger().warning(
            NeelixMessages.INVENTORY_ITEM_SLOT_IS_LOCKED.getMessage()
              .replace("{1}", String.valueOf(item.getItem().getType()))
          );

          continue;
        }

        this.inventory.setItem(item.getSlot(), item.getItem());
        this.lockedSlots.add(item.getSlot());
      }

      // Item with null slot value
      this.skipLockedSlots();
      this.inventory.setItem(this.currentSlot, item.getItem());
      this.currentSlot++;
    }
  }

  @Override
  public Inventory getInventory() { return this.inventory; }
}
