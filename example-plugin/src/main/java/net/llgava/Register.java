package net.llgava;

import net.llgava.commands.ListMinecraftItemsPaginatedCommand;
import net.llgava.commands.ListMinecraftItemsSimpleCommand;
import net.llgava.inventories.NeelixInventoryItem;
import net.llgava.inventories.NeelixPaginatedInventory;
import net.llgava.inventories.NeelixPaginatedNavigation;
import net.llgava.inventories.NeelixSimpleInventory;
import net.llgava.items.BaseItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static net.llgava.ExamplePlugin.*;

public class Register {
  private static final JavaPlugin plugin = getInstance();

  private static final List<NeelixInventoryItem> ITEMS = new ArrayList<>();
  private static final List<Integer> LOCKED_SLOTS = Resources.getCustomConfig().getConfig().getIntegerList("locked-slots");

  public static void commands() {
    plugin.getCommand("minecraft-items-simple").setExecutor(new ListMinecraftItemsSimpleCommand());
    plugin.getCommand("minecraft-items-paginated").setExecutor(new ListMinecraftItemsPaginatedCommand());
  }

  public static void inventories() {
    Neelix.getInventoryManager().registerInventory(
      new NeelixPaginatedInventory(54, "Minecraft Items", LOCKED_SLOTS, ITEMS, new NeelixPaginatedNavigation())
    );

    Neelix.getInventoryManager().registerInventory(
      new NeelixSimpleInventory(54, "Minecraft Items Simple", LOCKED_SLOTS, ITEMS)
    );
  }

  public static void items() {
    ITEMS.add(new BaseItem(13, new ItemStack(Material.DIAMOND_AXE)));

    int i = 0;
    for (Material material : Material.values()) {
      if (material.isItem() && !material.isAir()) {
        i++;

        if (i == 70) {
          ITEMS.add(new BaseItem(30, new ItemStack(Material.FEATHER)));
        } else if (i == 71) {
          ITEMS.add(new BaseItem(0, new ItemStack(Material.BAKED_POTATO)));
        } else if (i == 120) {
          ITEMS.add(new BaseItem(42, new ItemStack(Material.SPECTRAL_ARROW)));
        } else {
          ITEMS.add(new BaseItem(new ItemStack(material)));
        }
      }
    }
  }
}
