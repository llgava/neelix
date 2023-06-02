package net.llgava.items;

import lombok.Getter;
import lombok.Setter;
import net.llgava.inventories.NeelixInventory;
import net.llgava.utils.NeelixMessages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class NeelixInventoryItem {
  @Getter private String identifier;
  @Getter @Setter private Integer slot;
  @Getter @Setter private ItemStack item;
  @Getter private final UUID uuid;

  @Getter private ClickType clickType = null;
  @Getter private InventoryAction action = null;

  public NeelixInventoryItem(ItemStack item) {
    this(null, item);
  }

  public NeelixInventoryItem(String identifier, ItemStack item) {
    this(identifier, item, null);
  }

  public NeelixInventoryItem(String identifier, ItemStack item, Integer slot) {
    this.identifier = identifier;
    this.slot = slot;
    this.item = item == null ? new ItemStack(Material.AIR) : item;
    this.uuid = UUID.randomUUID();
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
