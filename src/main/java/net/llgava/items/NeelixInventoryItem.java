package net.llgava.items;

import lombok.Getter;
import lombok.Setter;
import net.llgava.inventories.NeelixInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class NeelixInventoryItem {
  @Getter private String identifier;
  @Getter @Setter private Integer slot;
  @Getter @Setter private ItemStack item;
  @Getter @Setter private boolean staticItem;
  @Getter private final UUID uuid;

  @Getter private ClickType clickType = null;
  @Getter private InventoryAction action = null;

  public NeelixInventoryItem(ItemStack item) {
    this(item, false);
  }

  public NeelixInventoryItem(ItemStack item, boolean staticItem) {
    this(null, item, staticItem);
  }

  public NeelixInventoryItem(String identifier, ItemStack item) {
    this(identifier, item, false);
  }

  public NeelixInventoryItem(String identifier, ItemStack item, boolean staticItem) {
    this(identifier, item, null, staticItem);
  }

  public NeelixInventoryItem(String identifier, ItemStack item, Integer slot) {
    this(identifier, item, slot, false);
  }

  public NeelixInventoryItem(String identifier, ItemStack item, Integer slot, boolean staticItem) {
    this.identifier = identifier;
    this.slot = slot;
    this.item = item == null ? new ItemStack(Material.AIR) : item;
    this.staticItem = staticItem;
    this.uuid = UUID.randomUUID();
  }

  /**
   * @return The name of the {@link ItemStack}
   * */
  public String getName() {
    return this.item.getItemMeta().getDisplayName();
  }

  /**
   * Logic to be triggered when clicked at this item.
   * @param inventory The opened inventory
   * @param whoClicked A Player instance of who clicked at the item
   * @param clickedSlot The clicked slot
   * @param clickedItem The clicked item
   */
  public abstract void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem);
}
