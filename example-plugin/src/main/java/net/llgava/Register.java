package net.llgava;

import net.llgava.commands.ListMinecraftItemsCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static net.llgava.ExamplePlugin.*;

public class Register {
  private static final JavaPlugin plugin = getInstance();

  public static void commands() {
    plugin.getCommand("minecraft-items").setExecutor(new ListMinecraftItemsCommand());
  }

  public static void items(List<ItemStack> itemList) {
    Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
      for (Material material : Material.values()) {
        if (material.isItem() && !material.isAir()) {
          itemList.add(new ItemStack(material));
        }
      }
    });
  }
}
