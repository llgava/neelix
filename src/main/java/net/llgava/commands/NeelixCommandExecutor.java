package net.llgava.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NeelixCommandExecutor implements TabExecutor {
  private static final ArrayList<String> EMPTY_TAB_COMPLETER = new ArrayList<>();

  private final String name;
  private final ArrayList<NeelixSubcommand> subcommands;

  public NeelixCommandExecutor(String name, ArrayList<NeelixSubcommand> subcommands) {
    this.name = name;
    this.subcommands = subcommands;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(!(sender instanceof Player)) { return true; }

    Player player = (Player) sender;

    if(args.length > 2 || args.length == 0) {
      return true;
    }

    if(command.getName().equals(this.name)) {
      NeelixSubcommand subcommand = this.getSubcommandByName(args[0]);

      if(subcommand == null) {
        return true;
      }

      if(!player.hasPermission(subcommand.getPermission()) && subcommand.getPermission() != null) {
        player.sendMessage("You don't have permission to use this command.");
        return true;
      }

      subcommand.execute(player, args);
    }

    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    ArrayList<String> completes = new ArrayList<>();

    if(args.length == 1) {
      for(NeelixSubcommand subcommand : this.subcommands) {
        if(subcommand.getName().startsWith(args[0])) {
          completes.add(subcommand.getName());
        }
      }

      return completes;
    }

    return EMPTY_TAB_COMPLETER;
  }

  public NeelixSubcommand getSubcommandByName(String name) {
    for(NeelixSubcommand subcommand : this.subcommands) {
      if(subcommand.getName().equalsIgnoreCase(name)) {
        return subcommand;
      }
    }

    return null;
  }
}
