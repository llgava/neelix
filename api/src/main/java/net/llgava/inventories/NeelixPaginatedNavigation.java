package net.llgava.inventories;

import lombok.Getter;
import net.llgava.items.NextInventoryPageItem;
import net.llgava.items.PreviousInventoryPageItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

  public void onClickNextNavigationItem(NeelixPaginatedInventory inventory, Player player, int clickedSlot) {
    if (clickedSlot == this.nextNavigationItem.getSlot()) {
      inventory.openInventoryOnNextPage(player);
    }
  }

  public void onClickPreviousNavigationItem(NeelixPaginatedInventory inventory, Player player, int clickedSlot) {
    if (clickedSlot == this.previousNavigationItem.getSlot()) {
      inventory.openInventoryOnPreviousPage(player);
    }
  }

  private static ItemStack defaultNavigationItem(String name) {
    ItemStack item = new ItemStack(Material.ARROW);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(ChatColor.RESET + name);
    item.setItemMeta(meta);

    return item;
  }
}
