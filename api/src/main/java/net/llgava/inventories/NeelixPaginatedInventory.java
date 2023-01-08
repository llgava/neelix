package net.llgava.inventories;


import lombok.Getter;
import net.llgava.utils.NeelixMessages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

import static net.llgava.Neelix.*;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private final NeelixPaginatedNavigation navigation;
  @Getter NeelixInventoryType type = NeelixInventoryType.PAGINATED;
  @Getter private final Map<Integer, Map<Integer, NeelixInventoryItem>> pages = new HashMap<>(); // <page, <slot, item>>

  @Getter private int currentlyOpenPage = 0;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items, NeelixPaginatedNavigation navigation) {
    super(size, title, lockedSlots, items);
    this.navigation = navigation;
    this.lockedSlots.addAll(this.navigation.asLockedSlots());

    this.mount();
  }

  @Override
  protected void mount() {
    int page = 0;
    List<Integer> lockedSlotsAdded = new ArrayList<>();
    Map<Integer, NeelixInventoryItem> pageItems = new HashMap<>();

    for (NeelixInventoryItem inventoryItem : this.items) {
      if (inventoryItem.getSlot() != null) {
        if (this.lockedSlots.contains(inventoryItem.getSlot())) {
          getNeelixLogger().warning(
            NeelixMessages.INVENTORY_ITEM_SLOT_IS_LOCKED.getMessage()
              .replace("{1}", String.valueOf(inventoryItem.getItem().getType()))
          );

          continue;
        }

        lockedSlotsAdded.add(inventoryItem.getSlot());
        pageItems.put(inventoryItem.getSlot(), inventoryItem);
        this.lockedSlots.add(inventoryItem.getSlot());

        continue;
      }

      this.skipLockedSlots();

      pageItems.put(this.currentSlot, inventoryItem);
      this.currentSlot++;

      if ((pageItems.size() - this.lockedSlots.size()) - 2 % this.getMaxItemsPerPage() + lockedSlotsAdded.size() == 0) {
        this.putNavigationItems(pageItems);
        this.pages.put(page, pageItems);

        page++;
        pageItems = new HashMap<>();
        this.lockedSlots.removeAll(lockedSlotsAdded);
        lockedSlotsAdded = new ArrayList<>();
        this.currentSlot = 0;
      }
    }

    this.putNavigationItems(pageItems);
    this.pages.put(page, pageItems);
  }

  private void putNavigationItems(Map<Integer, NeelixInventoryItem> currentPageItems) {
    currentPageItems.put(this.navigation.getNextNavigationItem().getSlot(), this.navigation.getNextNavigationItem());
    currentPageItems.put(this.navigation.getPreviousNavigationItem().getSlot(), this.navigation.getPreviousNavigationItem());
  }

  /** @return The max number of items that should be listed on available slots */
  public int getMaxItemsPerPage() {
    return this.size - this.lockedSlots.size();
  }

  /**
   * @param page The page to be opened
   * @return The {@link Inventory} with the specified page
   */
  public Inventory openInventoryOnPage(int page) {
    this.inventory.clear();
    int firstPage = 0;
    int lastPage = this.pages.size() - 1;

    if (page <= firstPage) { this.currentlyOpenPage = firstPage; }
    if (page >= lastPage) { this.currentlyOpenPage = lastPage; }
    if (!(page <= firstPage) && !(page >= lastPage)) { this.currentlyOpenPage = page; }

    this.pages.get(this.currentlyOpenPage).forEach((slot, inventoryItem) -> {
      this.inventory.setItem(slot, inventoryItem.getItem());
    });

    return this.inventory;
  }

  public void openInventoryOnNextPage(Player player) {
    this.currentlyOpenPage = this.currentlyOpenPage + 1;
    player.openInventory(this.openInventoryOnPage(this.currentlyOpenPage));
  }

  public void openInventoryOnPreviousPage(Player player) {
    this.currentlyOpenPage = this.currentlyOpenPage - 1;
    player.openInventory(this.openInventoryOnPage(this.currentlyOpenPage));
  }
}
