package net.llgava.inventories;


import lombok.Getter;
import net.llgava.Neelix;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixMessages;
import net.llgava.utils.NeelixUtils;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixSimpleInventory extends NeelixInventory {
  @Getter private final Map<Integer, NeelixInventoryItem> inventoryItems = new HashMap<>();

  /**
   * Create a full functional simple inventory.
   * @param size The size of the inventory.
   * @param title The title of the inventory.
   * @param lockedSlots Locked slots cannot allow items in it. If an item is added to a locked slot, it will be ignored.
   * @param items Items that must be added to the inventory.
   */
  public NeelixSimpleInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    super(size, title, lockedSlots, items);

    this.mount();
  }

  @Override
  protected void mount() {
    for (NeelixInventoryItem item : this.items) {

      // Inventory items limit
      if (this.currentSlot > this.size - 1) {
        Neelix.LOGGER.warning(
          NeelixUtils.parseMessage(
            NeelixMessages.INVENTORY_ITEMS_LIMIT_REACHED.getMessage(),
            this.title
          )
        );

        break;
      }

      // Item with non-null slot value
      if (item.getSlot() != null) {
        if (this.lockedSlots.contains(item.getSlot())) {
          Neelix.LOGGER.warning(
            NeelixUtils.parseMessage(
              NeelixMessages.INVENTORY_ITEM_SLOT_IS_LOCKED.getMessage(),
              String.valueOf(item.getSlot()),
              String.valueOf(item.getItem().getType())
            )
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
  public NeelixInventoryItem getClickedItem(int slot) {
    return this.getInventoryItems().get(slot);
  }
}
