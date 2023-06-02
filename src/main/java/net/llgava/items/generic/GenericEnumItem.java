package net.llgava.items.generic;

import lombok.Getter;
import lombok.Setter;
import net.llgava.inventories.NeelixInventory;
import net.llgava.items.NeelixInventoryItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericEnumItem extends NeelixInventoryItem implements GenericItem<Integer> {
  private int value;
  private final int initialValue;
  @Getter private final List<ItemStack> options = new ArrayList<>();
  private static final ItemStack AIR = new ItemStack(Material.AIR);
  private boolean hasInitialItem;

  public GenericEnumItem(String identifier, int initialValue, int slot, ItemStack... items) {
    super(identifier, items.length > 0 ? Arrays.asList(items).get(initialValue) : AIR, slot);

    this.value = initialValue;
    this.initialValue = initialValue;
    this.hasInitialItem = items.length > 0;
    this.options.addAll(
      items.length > 0
        ? Arrays.asList(items)
        : Collections.singletonList(AIR)
      );
  }

  @Override
  public void onClick(NeelixInventory inventory, Player whoClicked, int clickedSlot, ItemStack clickedItem) {
    inventory.getInventory().setItem(clickedSlot, this.update());
  }

  public ItemStack getItemByIndex(int index) {
    return this.options.get(index);
  }

  public GenericEnumItem addOption(ItemStack item) {
    if (this.options.get(0).equals(AIR) && !this.hasInitialItem) {
      this.options.set(0, item);
      this.hasInitialItem = true;
      this.setItem(item);

      return this;
    }

    this.options.add(item);
    return this;
  }

  public GenericEnumItem removeOption(int index) {
    this.options.remove(index);
    return this;
  }

  public ItemStack update() {
    this.value++;
    if (this.value > this.options.size() - 1) this.value = 0;
    this.setItem(this.getItemByIndex(this.value));

    return this.options.get(this.value);
  }

  @Override
  public Integer getValue() { return this.value; }

  @Override
  public void setValue(Integer value) { this.value = value; }

  @Override
  public void reset() {
    this.value = this.initialValue;
    this.setItem(this.getItemByIndex(this.value));
  }
}
