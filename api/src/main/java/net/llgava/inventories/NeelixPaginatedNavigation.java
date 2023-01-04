package net.llgava.inventories;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NeelixPaginatedNavigation {
  @Getter private final NeelixNavigationItem nextNavigationItem;
  @Getter private final NeelixNavigationItem previousNavigationItem;

  public NeelixPaginatedNavigation() {
    this(defaultNavigationItem(8, "Next page"), defaultNavigationItem(0, "Previous page"));
  }

  public NeelixPaginatedNavigation(NeelixNavigationItem next, NeelixNavigationItem previous) {
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

  private static NeelixNavigationItem defaultNavigationItem(int slot, String name) {
    ItemStack item = new ItemStack(Material.ARROW);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(ChatColor.RESET + name);
    item.setItemMeta(meta);

    return new NeelixNavigationItem(slot, item);
  }
}
