package net.llgava;

import net.llgava.commands.ListMinecraftItemsCommand;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import net.llgava.inventories.NeelixPaginatedNavigation;
import net.llgava.items.BaseItem;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static net.llgava.ExamplePlugin.*;

public class Register {
  private static final JavaPlugin plugin = getInstance();

  private static final List<NeelixInventoryItem> ITEMS = new ArrayList<>();
  private static final List<Integer> LOCKED_SLOTS = Resources.getCustomConfig().getConfig().getIntegerList("locked-slots");

  public static void commands() {
    plugin.getCommand("minecraft-items").setExecutor(new ListMinecraftItemsCommand());
  }

  public static void inventories() {
    Neelix.getInventoryManager().registerInventory(
      new NeelixPaginatedInventory(54, "Minecraft Items", LOCKED_SLOTS, ITEMS, new NeelixPaginatedNavigation())
    );
  }

  public static void items() {
    for (Material material : Material.values()) {
      if (material.isItem() && !material.isAir()) {
        ITEMS.add(
          new BaseItem(new org.bukkit.inventory.ItemStack(material))
        );
      }
    }
  }
}
