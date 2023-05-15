package net.llgava.inventories;

import lombok.Getter;
import net.llgava.items.NextInventoryPageItem;
import net.llgava.items.PreviousInventoryPageItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NeelixPaginatedNavigation {
  @Getter private final NextInventoryPageItem nextNavigationItem;
  @Getter private final PreviousInventoryPageItem previousNavigationItem;

  public NeelixPaginatedNavigation() {
    this(
      new NextInventoryPageItem(8, defaultNavigationItem("Next page")),
      new PreviousInventoryPageItem(0, defaultNavigationItem("Previous page"))
    );
  }

  public NeelixPaginatedNavigation(NextInventoryPageItem next, PreviousInventoryPageItem previous) {
    this.nextNavigationItem = next;
    this.previousNavigationItem = previous;
  }

  /** @return A list with the slots to be locked. */
  public List<Integer> lockSlots() {
    List<Integer> list = new ArrayList<>();
    list.add(this.previousNavigationItem.getSlot());
    list.add(this.nextNavigationItem.getSlot());

    return list;
  }

  /** @return A default ItemStack. */
  private static ItemStack defaultNavigationItem(String name) {
    ItemStack item = new ItemStack(Material.ARROW);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(ChatColor.RESET + name);
    item.setItemMeta(meta);

    return item;
  }
}
