package net.llgava.inventories;

import lombok.Getter;
import net.llgava.inventories.handlers.OpenNeelixInventoryHandle;
import net.llgava.inventories.handlers.CloseNeelixInventoryHandle;
import net.llgava.inventories.handlers.FullNeelixInventoryHandle;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixInventoryType;
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
  @Getter protected final NeelixInventoryType type = NeelixInventoryType.NONE;
  protected Inventory inventory;
  protected int currentSlot;

  public NeelixInventory(int size, String title, @Nullable List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    this.currentSlot = 0;
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots != null ? lockedSlots : new ArrayList<>();
    this.items = items;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);
  }

  /**
   * Main method which should contain all the logic to build the inventory items.
   * */
  protected abstract void mount();

  /**
   * @return The {@link Inventory}.
   * */
  public abstract Inventory getInventory();

  /**
   * @param clickedSlot The clicked slot on the inventory.
   * @return The clicked item by the clicked slot.
   * */
  public abstract NeelixInventoryItem getClickedItem(int clickedSlot);

  /**
   * Resets the inventory to initial configured state.
   * */
  public void reset() {
    for (NeelixInventoryItem item: this.items) {
      this.lockedSlots.remove(item.getSlot());
    }

    this.inventory.clear();
    this.mount();
  }

  /**
   * Avoid all configured locked slots when mounting the inventory.
   * */
  protected void skipLockedSlots() {
    if(this.lockedSlots.contains(this.currentSlot)) {
      do {
        this.currentSlot++;
      } while (this.lockedSlots.contains(this.currentSlot));
    }
  }

  /**
   * {@link FullNeelixInventoryHandle} are inventories that trigger an event when opened and closed.
   * @return If the inventory is an instance of full handled inventory.
   */
  public boolean isFullHandled() { return this instanceof FullNeelixInventoryHandle; }

  /**
   * {@link OpenNeelixInventoryHandle} are inventories that trigger an event when opened.
   * @return If the inventory is an instance of open handled inventory.
   */
  public boolean hasOpenHandled()  { return this instanceof OpenNeelixInventoryHandle; }

  /**
   * {@link CloseNeelixInventoryHandle} are inventories that trigger an event when closed.
   * @return If the inventory is an instance of close handled inventory.
   */
  public boolean hasCloseHandled()  { return this instanceof CloseNeelixInventoryHandle; }

  /**
   * @deprecated - Use <i>instanceof</i> instead.
   * @return If the inventory is an instance of simple inventory.
   */
  public boolean isSimpleInventory() { return this.getType() == NeelixInventoryType.SIMPLE; }

  /**
   * @deprecated - Use <i>instanceof</i> instead.
   * @return If the inventory is an instance of paginated inventory.
   */
  public boolean isPaginatedInventory() { return this.getType() == NeelixInventoryType.PAGINATED; }
}
