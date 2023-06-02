package net.llgava.items.generic;

import org.bukkit.inventory.ItemStack;

public interface GenericItem<T> {
  /** Resets the value for the initial value passed on the constructor. */
  void reset();

  void setValue(T value);

  T getValue();
}
