package net.llgava.inventories;


import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private final List<ItemStack> items;
  @Getter private final Map<Integer, Map<Integer, ItemStack>> pages = new HashMap<>();
  @Getter private final NeelixPaginatedNavigation navigation;
  @Getter NeelixInventoryType type = NeelixInventoryType.PAGINATED;

  @Getter private int currentOpenedPage = 0;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<ItemStack> items, NeelixPaginatedNavigation navigation) {
    super(size, title, lockedSlots);
    this.navigation = navigation;
    this.items = items;

    this.mountInventoryPages();
  }

  private void mountNavigation() {
    NeelixNavigationItem next =  this.navigation.getNextNavigationItem();
    NeelixNavigationItem previous =  this.navigation.getPreviousNavigationItem();

    if (!this.lockedSlots.contains(next.getSlot())) { this.lockedSlots.add(next.getSlot()); }
    if (!this.lockedSlots.contains(previous.getSlot())) { this.lockedSlots.add(previous.getSlot()); }

    this.inventory.setItem(next.getSlot(), next.getItem());
    this.inventory.setItem(previous.getSlot(), previous.getItem());
  }

  private void mountInventoryPages() {
    this.mountNavigation();

    int currentItemIndex = 0;
    int currentPageIndex = 0;
    Map<Integer, ItemStack> currentPageItems = new HashMap<>();

    for (ItemStack item : this.items) {
      this.skipLockedSlots();

      currentPageItems.put(this.currentSlot, item);
      currentItemIndex++;
      this.currentSlot++;

      if (currentItemIndex % this.getMaxItemsPerPage() == 0) {
        this.pages.put(currentPageIndex, currentPageItems);

        currentPageIndex++;
        currentPageItems = new HashMap<>();
        this.currentSlot = 0;
      }
    }

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

    this.pages.get(this.currentOpenedPage).forEach(this.inventory::setItem);

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
