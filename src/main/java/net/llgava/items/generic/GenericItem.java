package net.llgava.items.generic;

public interface GenericItem<T> {
  /** Resets the value for the initial value passed on the constructor. */
  void reset();

  void setValue(T value);

  T getValue();
}
