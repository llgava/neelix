package net.llgava.inventories;

import lombok.Getter;
import net.llgava.items.NextInventoryPageItem;
import net.llgava.items.PreviousInventoryPageItem;
import net.llgava.utils.NeelixDefaultValues;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NeelixPaginatedNavigation {
  @Getter private final NextInventoryPageItem nextNavigationItem;
  @Getter private final PreviousInventoryPageItem previousNavigationItem;

  /**
   * Build the navigation items to work with the {@link NeelixPaginatedInventory} logic. <br />
   * This constructor uses <b>pre-defined</b> navigation items.
   */
  public NeelixPaginatedNavigation() {
    this(
      new NextInventoryPageItem(defaultNavigationItem(NeelixDefaultValues.NEXT_STACK_TITLE.getString()), NeelixDefaultValues.NEXT_STACK_SLOT.getInt()),
      new PreviousInventoryPageItem(defaultNavigationItem(NeelixDefaultValues.PREVIOUS_STACK_TITLE.getString()), NeelixDefaultValues.PREVIOUS_STACK_SLOT.getInt())
    );
  }

  /**
   * Build the navigation items to work with the {@link NeelixPaginatedInventory} logic.
   *
   * @param next The item responsible for "next page" interaction.
   * @param previous The item responsible for "previous page" interaction.
   */
  public NeelixPaginatedNavigation(NextInventoryPageItem next, PreviousInventoryPageItem previous) {
    this.nextNavigationItem = next;
    this.previousNavigationItem = previous;
  }

  /**
   * Build a <b>pre-defined</b> navigation item.
   * */
  private static ItemStack defaultNavigationItem(String name) {
    ItemStack item = new ItemStack(Material.ARROW);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(ChatColor.RESET + name);
    item.setItemMeta(meta);

    return item;
  }
}
