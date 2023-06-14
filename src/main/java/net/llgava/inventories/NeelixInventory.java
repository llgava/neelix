package net.llgava.inventories;

import lombok.Getter;
import net.llgava.Neelix;
import net.llgava.inventories.handlers.OpenNeelixInventoryHandle;
import net.llgava.inventories.handlers.CloseNeelixInventoryHandle;
import net.llgava.inventories.handlers.FullNeelixInventoryHandle;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixMessages;
import net.llgava.utils.NeelixUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class NeelixInventory {
  @Getter protected final int size;
  @Getter protected final String title;
  @Getter protected List<Integer> lockedSlots;
  @Getter protected final List<NeelixInventoryItem> items;
  protected Inventory inventory;
  protected int currentSlot;

  public NeelixInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items) {
    this.currentSlot = 0;
    this.size = size;
    this.title = title;
    this.lockedSlots = lockedSlots != null ? lockedSlots : new ArrayList<>();
    this.items = items;
    this.inventory = Bukkit.createInventory(null, this.size, this.title);

    this.setupStaticItemsSlot();
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
   * Static items are items that should automatically lock the slots and if the
   * inventory be a {@link NeelixPaginatedInventory}, be present in every page.
   * */
  private void setupStaticItemsSlot() {
    for (NeelixInventoryItem item : this.items) {
      if (item.isStaticItem()) {
        this.addLockedSlot(item.getSlot());
      }
    }
  }

  public ArrayList<NeelixInventoryItem> getStaticItems() {
    ArrayList<NeelixInventoryItem> staticItems = new ArrayList<>();

    for (NeelixInventoryItem item : this.items) {
      if (item.isStaticItem()) {
        staticItems.add(item);
      }
    }

    return staticItems;
  }

  /**
   * Resets the inventory to initial configured state.
   * */
  public void reset() {
    for (NeelixInventoryItem item : this.items) {
      this.lockedSlots.remove(item.getSlot());
    }

    this.inventory.clear();
    this.currentSlot = 0;
    this.mount();
  }

  /**
   * Add new item to the inventory.
   * @param item The item to be added.
   * */
  public void addItem(NeelixInventoryItem item) {
    this.items.add(item);

    this.reset();
  }

  /**
   * Return an item from the inventory.
   * @param name The name of the item.
   * */
  public NeelixInventoryItem getItemByName(String name) {
    for (NeelixInventoryItem item : this.items) {
      if (ChatColor.stripColor(item.getItem().getItemMeta().getDisplayName()).equals(name)) {
        return item;
      }
    }

    return null;
  }

  /**
   * Remove an item from the inventory.
   * @param name The name of the item.
   * */
  public void removeItemByName(String name) {
    NeelixInventoryItem item = this.getItemByName(name);
    this.items.remove(item);
    this.lockedSlots.removeIf(slot -> slot.equals(item.getSlot()));

    this.reset();
  }

  /**
   * Add new slots to locked slots list.
   * @param slots The slots number.
   * */
  public void addLockedSlot(int... slots) {
    for (int slot : slots) {
      this.addLockedSlot(slot);
    }
  }

  /**
   * Add a new slot to locked slots list.
   * @param slot The slot number.
   * */
  public void addLockedSlot(int slot) {
    if (this.lockedSlots.contains(slot)) {
      Neelix.LOGGER.warning(
        NeelixUtils.parseMessage(
          NeelixMessages.DUPLICATED_LOCKED_SLOT.getMessage(),
          String.valueOf(slot)
        )
      );

      return;
    }

    this.lockedSlots.add(slot);
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
}
