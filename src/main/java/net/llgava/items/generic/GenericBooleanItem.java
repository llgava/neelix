package net.llgava.items.generic;

import lombok.Getter;
import net.llgava.inventories.NeelixInventory;
import net.llgava.items.NeelixInventoryItem;
import net.llgava.utils.NeelixDefaultValues;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GenericBooleanItem extends NeelixInventoryItem implements GenericItem<Boolean> {
  private boolean value;
  private final boolean initialValue;
  @Getter private ItemStack trueStack;
  @Getter private ItemStack falseStack;

  public GenericBooleanItem(String identifier, boolean initialValue) {
    this(
      identifier,
      createBooleanItem(Material.LIME_DYE, NeelixDefaultValues.TRUE_ITEM_TITLE.getString()),
      createBooleanItem(Material.GRAY_DYE, NeelixDefaultValues.FALSE_ITEM_TITLE.getString()),
      initialValue
    );
  }

  public GenericBooleanItem(String identifier, boolean initialValue, int slot) {
    this(
      identifier,
      createBooleanItem(Material.LIME_DYE, NeelixDefaultValues.TRUE_ITEM_TITLE.getString()),
      createBooleanItem(Material.GRAY_DYE, NeelixDefaultValues.FALSE_ITEM_TITLE.getString()),
      initialValue,
      slot
    );
  }

  public GenericBooleanItem(String identifier, ItemStack trueStack, ItemStack falseStack, boolean initialValue) {
    super(identifier, initialValue ? trueStack : falseStack);

    this.value = initialValue;
    this.initialValue = initialValue;
    this.trueStack = trueStack;
    this.falseStack = falseStack;
  }

  public GenericBooleanItem(String identifier, ItemStack trueStack, ItemStack falseStack, boolean initialValue, int slot) {
    super(identifier, initialValue ? trueStack : falseStack, slot);

    this.value = initialValue;
    this.initialValue = initialValue;
    this.trueStack = trueStack;
    this.falseStack = falseStack;
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    this.setValue(!this.value);
    inventory.getInventory().setItem(clickedSlot, this.update());
  }

  /**
   * Generate a recommended ItemStack format for boolean items.
   *
   * @param material The ItemStack material.
   * @param title The ItemStack title.
   * */
  private static ItemStack createBooleanItem(Material material, String title) {
    ItemStack item = new ItemStack(material, 1);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(title);
    item.setItemMeta(meta);

    return item;
  }

  public ItemStack update() { return this.value ? this.trueStack : this.falseStack; }

  @Override
  public Boolean getValue() { return this.value; }

  @Override
  public void setValue(Boolean value) { this.value = value; }

  @Override
  public void reset() { this.value = this.initialValue; }
}
