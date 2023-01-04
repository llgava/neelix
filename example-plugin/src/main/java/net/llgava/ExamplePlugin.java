package net.llgava;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ExamplePlugin extends JavaPlugin {
  @Getter private static ExamplePlugin instance;
  @Getter @Setter private static List<ItemStack> items = new ArrayList<>();

  @Override
  public void onEnable() {
    instance = this;
    Neelix.init(this);

    Resources.Registry();
    Register.commands();
    Register.items(items);
    Register.inventories();
  }
}
