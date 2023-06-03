package net.llgava.utils;

import lombok.Getter;
import net.llgava.inventories.NeelixPaginatedInventory;
import net.llgava.inventories.NeelixSimpleInventory;

/**
 * @deprecated - Use <i>instanceof</i> instead.
 */
public enum NeelixInventoryType {
  NONE(0, null),
  SIMPLE(1, NeelixSimpleInventory.class),
  PAGINATED(2, NeelixPaginatedInventory.class);

  @Getter private final int index;
  @Getter private final Class<?> object;

  NeelixInventoryType(int index, Class<?> object) {
    this.index = index;
    this.object = object;
  }
}
