package net.llgava.inventories;

import lombok.Getter;

public enum NeelixInventoryType {
  NONE(0, null),
  SIMPLE(1, NeelixSimpleInventory.class),
  PAGINATED(2, NeelixPaginatedInventory.class),
  CONFIRMATION(3, null);

  @Getter private final int index;
  @Getter private final Class<?> object;

  NeelixInventoryType(int index, Class<?> object) {
    this.index = index;
    this.object = object;
  }
}
