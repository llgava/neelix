package net.llgava.commands;

import net.llgava.Neelix;
import net.llgava.inventories.NeelixSimpleInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListMinecraftItemsSimpleCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String _label, String[] args) {
    if (!(sender instanceof Player)) return true;
    Player player = (Player) sender;

    NeelixSimpleInventory inventory = (NeelixSimpleInventory) Neelix.getInventoryManager().getInventoryByTitle("Minecraft Items Simple");

    player.openInventory(inventory.getInventory());

    return true;
  }
}
