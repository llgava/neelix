package net.llgava.utils;

import lombok.Getter;

public enum NeelixConfirmationInventorySize {
  SMALL(9),
  MEDIUM(27),
  BIG(54);

  @Getter private final int size;

  NeelixConfirmationInventorySize(int size) {
    this.size = size;
  }
}
