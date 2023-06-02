package net.llgava.inventories;

import lombok.Getter;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixInventoryType;
import net.llgava.utils.NeelixMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class NeelixPaginatedInventory extends NeelixInventory {
  @Getter private int openedPage = 0;
  @Getter private final Map<Integer, Map<Integer, NeelixInventoryItem>> pages = new HashMap<>(); // <page, <slot, item>>
  @Getter private final NeelixPaginatedNavigation navigation;
  @Getter private final NeelixInventoryType type = NeelixInventoryType.PAGINATED;

  public NeelixPaginatedInventory(int size, String title, List<Integer> lockedSlots, List<NeelixInventoryItem> items, NeelixPaginatedNavigation navigation) {
    super(size, title, lockedSlots, items);
    this.navigation = navigation;
    this.lockedSlots.addAll(this.navigation.asLockedSlots());

    this.mount();
  }

  @Override
  protected void mount() {
    int page = 0;
    ArrayList<Integer> pageLockedSlots = new ArrayList<>();
    HashMap<Integer, NeelixInventoryItem> items = new HashMap<>();

    for (NeelixInventoryItem item : this.items) {
      boolean isLastItem = this.items.indexOf(item) == this.items.size();

      // Page items limit
      if (items.size() >= this.getMaxItemsPerPage() || isLastItem) {
        this.putNavigationItems(items);
        this.pages.put(page, items);

        page++;
        this.currentSlot = 0;
        items = new HashMap<>();
        pageLockedSlots = new ArrayList<>();
      }

      // Items with non-null slot value
      if (item.getSlot() != null) {
        if (this.lockedSlots.contains(item.getSlot()) || pageLockedSlots.contains(item.getSlot())) {
          Bukkit.getServer().getLogger().warning(
            NeelixMessages.INVENTORY_ITEM_SLOT_IS_LOCKED.getMessage()
              .replace("{1}", String.valueOf(item.getSlot()))
              .replace("{2}", String.valueOf(item.getItem().getType()))
          );

          continue;
        }

        items.put(item.getSlot(), item);
        pageLockedSlots.add(item.getSlot());

        continue;
      }

      // Items with null slot value
      this.skipLockedSlots();

      items.put(this.currentSlot, item);
      pageLockedSlots.add(this.currentSlot);
      this.currentSlot++;
    }

    this.putNavigationItems(items);
    this.pages.put(page, items);
  }

  /** @return The max number of items that should be listed on available slots */
  public int getMaxItemsPerPage() {
    return this.size - this.lockedSlots.size();
  }

  /** Add navigation items to the current inventory. */
  private void putNavigationItems(HashMap<Integer, NeelixInventoryItem> items) {
    items.put(this.navigation.getNextNavigationItem().getSlot(), this.navigation.getNextNavigationItem());
    items.put(this.navigation.getPreviousNavigationItem().getSlot(), this.navigation.getPreviousNavigationItem());
  }

  /**
   * @param page The page to be opened
   * @return The {@link Inventory} with the specified page
   */
  public Inventory openInventoryOnPage(int page) {
    this.inventory.clear();
    int firstPage = 0;
    int lastPage = this.pages.size() - 1;

    if (page <= firstPage) { this.openedPage = firstPage; }
    if (page >= lastPage) { this.openedPage = lastPage; }
    if (!(page <= firstPage) && !(page >= lastPage)) { this.openedPage = page; }

    this.pages.get(this.openedPage).forEach((slot, item) -> {
      this.inventory.setItem(slot, item.getItem());
    });

    return this.inventory;
  }

  /** Open the current inventory one page forward. */
  public void openInventoryOnNextPage(Player player) {
    this.openedPage = this.openedPage + 1;
    player.openInventory(this.openInventoryOnPage(this.openedPage));
  }

  /** Open the current inventory one page back. */
  public void openInventoryOnPreviousPage(Player player) {
    this.openedPage = this.openedPage - 1;
    player.openInventory(this.openInventoryOnPage(this.openedPage));
  }

  /** Returns the {@link Inventory} on the first page. */
  @Override
  public Inventory getInventory() { return this.openInventoryOnPage(0); }

  @Override
  public NeelixInventoryItem getClickedItem(int clickedSlot) {
    return this.getPages().get(this.getOpenedPage()).get(clickedSlot);
  }
}
