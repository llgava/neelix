package net.llgava.inventories;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private final List<ItemStack> items;
  @Getter private final Map<Integer, Map<Integer, ItemStack>> pages = new HashMap<>();

  @Getter private int currentOpenedPage = 0;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<ItemStack> items) {
    super(size, title, lockedSlots);
    this.items = items;

    //this.inventory = Bukkit.createInventory(null, this.size, this.title);
    this.mountInventoryPages();
  }

  private void mountInventoryPages() {
    int currentItemIndex = 0;
    int currentPageIndex = 0;
    Map<Integer, ItemStack> currentPageItems = new HashMap<>();

    for (ItemStack item : this.items) {
      this.skipLockedSlots();

      currentPageItems.put(this._currentSlot, item);
      currentItemIndex++;
      this._currentSlot++;

      if (currentItemIndex % this.getMaxItemsPerPage() == 0) {
        this.pages.put(currentPageIndex, currentPageItems);

        currentPageIndex++;
        currentPageItems = new HashMap<>();
        this._currentSlot = 0;
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

    this.pages.get(this.currentOpenedPage).forEach((slot, item) -> {
      this.inventory.setItem(slot, item);
    });

    return this.inventory;
  }

  public Inventory openInventoryOnNextPage() {
    return this.openInventoryOnPage(this.currentOpenedPage + 1);
  }

  public Inventory openInventoryOnPreviousPage() {
    return this.openInventoryOnPage(this.currentOpenedPage - 1);
  }
}
