package net.llgava.inventories;


import lombok.Getter;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixInventoryType;
import net.llgava.utils.NeelixMessages;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter private final NeelixInventoryType type = NeelixInventoryType.SIMPLE;
  @Getter private final Map<Integer, NeelixInventoryItem> inventoryItems = new HashMap<>();

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

        this.inventoryItems.put(item.getSlot(), item);
        this.inventory.setItem(item.getSlot(), item.getItem());
        this.lockedSlots.add(item.getSlot());

        continue;
      }

      // Item with null slot value
      this.skipLockedSlots();
      this.inventoryItems.put(this.currentSlot, item);
      this.inventory.setItem(this.currentSlot, item.getItem());
      this.currentSlot++;
    }
  }

  @Override
  public Inventory getInventory() { return this.inventory; }

  @Override
  public NeelixInventoryItem getClickedItem(int clickedSlot) {
    return this.getInventoryItems().get(clickedSlot);
  }
}
