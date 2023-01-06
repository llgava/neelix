package net.llgava.inventories;


import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private final List<NeelixInventoryItem> items;
  @Getter private final NeelixPaginatedNavigation navigation;
  @Getter NeelixInventoryType type = NeelixInventoryType.PAGINATED;
  @Getter private Map<Integer, Map<Integer, NeelixInventoryItem>> pages = new HashMap<>(); // <page, <slot, item>>

  @Getter private int currentOpenedPage = 0;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items, NeelixPaginatedNavigation navigation) {
    super(size, title, lockedSlots);
    this.navigation = navigation;
    this.items = items;

    this.mountInventoryPages();
  }

  /** Add navigation items slot on locked slots to prevent item overwritten. */
  private void updateLockedSlots() {
    int nextSlot = this.navigation.getNextNavigationItem().getSlot();
    int previousSlot = this.navigation.getPreviousNavigationItem().getSlot();

    if (!this.lockedSlots.contains(nextSlot)) { this.lockedSlots.add(nextSlot); }
    if (!this.lockedSlots.contains(previousSlot)) { this.lockedSlots.add(previousSlot); }
  }

  private void mountNavigation(Map<Integer, NeelixInventoryItem> currentPageItems) {
    currentPageItems.put(this.navigation.getNextNavigationItem().getSlot(), this.navigation.getNextNavigationItem());
    currentPageItems.put(this.navigation.getPreviousNavigationItem().getSlot(), this.navigation.getPreviousNavigationItem());
  }

  private void mountInventoryPages() {
    this.updateLockedSlots();

    int currentItemIndex = 0;
    int currentPageIndex = 0;
    Map<Integer, NeelixInventoryItem> currentPageItems = new HashMap<>();

    for (NeelixInventoryItem inventoryItem : this.items) {
      this.skipLockedSlots();

      currentPageItems.put(this.currentSlot, inventoryItem);
      currentItemIndex++;
      this.currentSlot++;

      if (currentItemIndex % this.getMaxItemsPerPage() == 0) {
        this.mountNavigation(currentPageItems);
        this.pages.put(currentPageIndex, currentPageItems);

        currentPageIndex++;
        currentPageItems = new HashMap<>();
        this.currentSlot = 0;
      }
    }

    this.mountNavigation(currentPageItems);
    this.pages.put(currentPageIndex, currentPageItems);
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

    if (page <= firstPage) { this.currentOpenedPage = firstPage; }
    if (page >= lastPage) { this.currentOpenedPage = lastPage; }
    if (!(page <= firstPage) && !(page >= lastPage)) { this.currentOpenedPage = page; }

    this.pages.get(this.currentOpenedPage).forEach((slot, inventoryItem) -> {
      this.inventory.setItem(slot, inventoryItem.getItem());
    });

    return this.inventory;
  }

  public void openInventoryOnNextPage(Player player) {
    this.currentOpenedPage = this.currentOpenedPage + 1;
    player.openInventory(this.openInventoryOnPage(this.currentOpenedPage));
  }

  public void openInventoryOnPreviousPage(Player player) {
    this.currentOpenedPage = this.currentOpenedPage - 1;
    player.openInventory(this.openInventoryOnPage(this.currentOpenedPage));
  }
}
