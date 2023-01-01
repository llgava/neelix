package net.llgava.commands;

import net.llgava.Resources;
import net.llgava.inventories.NeelixPaginatedInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static net.llgava.ExamplePlugin.*;

public class ListMinecraftItemsCommand implements CommandExecutor {
  private static final List<Integer> lockedSlots = Resources.getCustomConfig().getConfig().getIntegerList("locked-slots");

  @Override
  public boolean onCommand(CommandSender sender, Command command, String _label, String[] args) {
    if (!(sender instanceof Player)) return true;
    Player player = (Player) sender;

    NeelixPaginatedInventory inventory = new NeelixPaginatedInventory(54, "Minecraft Items", lockedSlots, getItems());

    if (args.length == 0 || !this.isIntegerArgument(args[0])) {
      player.openInventory(inventory.openInventoryOnPage(0));
      return true;
    }

    int page = Integer.parseInt(args[0]);
    player.openInventory(inventory.openInventoryOnPage(page));

    return true;
  }

  private boolean isIntegerArgument(String argument) {
    try {
      Integer.parseInt(argument);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
