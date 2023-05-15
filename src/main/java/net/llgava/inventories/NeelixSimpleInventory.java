package net.llgava.inventories;


import lombok.Getter;
import net.llgava.utils.NeelixInventoryType;
import net.llgava.utils.NeelixMessages;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.llgava.Neelix.*;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter NeelixInventoryType type = NeelixInventoryType.SIMPLE;
  @Getter Map<Integer, NeelixInventoryItem> parsedItem = new HashMap<>();

  public NeelixSimpleInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    super(size, title, lockedSlots, items);
    this.mount();
  }

  @Override
  protected void mount() {
    for (NeelixInventoryItem inventoryItem : this.items) {
      if (inventoryItem.getSlot() != null) {
        if (this.lockedSlots.contains(inventoryItem.getSlot())) {
          Bukkit.getServer().getLogger().warning(
            NeelixMessages.INVENTORY_ITEM_SLOT_IS_LOCKED.getMessage()
              .replace("{1}", String.valueOf(inventoryItem.getItem().getType()))
          );

          continue;
        }

        this.parsedItem.put(inventoryItem.getSlot(), inventoryItem);
        this.inventory.setItem(inventoryItem.getSlot(), inventoryItem.getItem());

        if (!this.lockedSlots.contains(inventoryItem.getSlot())) {
          this.lockedSlots.add(inventoryItem.getSlot());
        }

        continue;
      }

      this.skipLockedSlots();

      if (this.currentSlot > this.size - 1) {
        Bukkit.getServer().getLogger().warning(
          NeelixMessages.INVENTORY_ITEMS_LIMIT_REACHED.getMessage()
            .replace("{1}", this.title)
        );
        break;
      }

      this.parsedItem.put(this.currentSlot, inventoryItem);
      this.inventory.setItem(this.currentSlot, inventoryItem.getItem());
      this.currentSlot++;
    }
  }

  @Override
  public Inventory getInventory() { return this.inventory; }
}
