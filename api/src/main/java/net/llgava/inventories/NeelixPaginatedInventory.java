package net.llgava.inventories;


import lombok.Getter;
import net.llgava.items.NextInventoryPageItem;
import net.llgava.items.PreviousInventoryPageItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private final List<NeelixInventoryItem> items;
  @Getter private final NeelixPaginatedNavigation navigation;
  @Getter NeelixInventoryType type = NeelixInventoryType.PAGINATED;
  @Getter private final Map<Integer, Map<Integer, NeelixInventoryItem>> pages = new HashMap<>(); // <page, <slot, item>>

  @Getter private int currentOpenedPage = 0;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items, NeelixPaginatedNavigation navigation) {
    super(size, title, lockedSlots);
    this.navigation = navigation;
    this.items = items;

    this.mountInventoryPages();
  }

  private void mountNavigation(Map<Integer, NeelixInventoryItem> currentPageItems) {
    NextInventoryPageItem next =  this.navigation.getNextNavigationItem();
    PreviousInventoryPageItem previous =  this.navigation.getPreviousNavigationItem();

    if (!this.lockedSlots.contains(next.getSlot())) { this.lockedSlots.add(next.getSlot()); }
    if (!this.lockedSlots.contains(previous.getSlot())) { this.lockedSlots.add(previous.getSlot()); }

    currentPageItems.put(next.getSlot(), next);
    currentPageItems.put(previous.getSlot(), previous);
  }

  private void mountInventoryPages() {

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
