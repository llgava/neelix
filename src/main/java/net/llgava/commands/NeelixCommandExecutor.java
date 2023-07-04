package net.llgava.commands;

import net.llgava.events.PlayerWithoutSubcommandPermissionEvent;
import net.llgava.events.SubcommandNotFoundEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeelixCommandExecutor implements TabExecutor {
  private static final ArrayList<String> EMPTY_TAB_COMPLETER = new ArrayList<>();

  private final String name;
  private final List<NeelixSubcommand> subcommands;

  /**
   * This executor is not a substitute for {@link CommandExecutor} or {@link TabExecutor}! <br />
   * Should be used only for {@link NeelixSubcommand} architecture.
   * @param name The parent command name.
   * @param subcommands All the subcommands linked to the parent command.
   */
  public NeelixCommandExecutor(String name, NeelixSubcommand... subcommands) {
    this.name = name;
    this.subcommands = Arrays.asList(subcommands);
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
        Bukkit.getServer().getPluginManager()
          .callEvent(new SubcommandNotFoundEvent(player));
        return true;
      }

      if(!player.hasPermission(subcommand.getPermission()) && !subcommand.getPermission().isEmpty()) {
        Bukkit.getServer().getPluginManager()
          .callEvent(new PlayerWithoutSubcommandPermissionEvent(player, command, subcommand));
        return true;
      }

      subcommand.execute(player, args);
    }

    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    Player player = (Player) sender;
    List<String> completes = new ArrayList<>();

    if(args.length == 1) {
      for(NeelixSubcommand subcommand : this.subcommands) {
        if(subcommand.getName().startsWith(args[0])) {
          if(player.hasPermission(subcommand.getPermission()) || subcommand.getPermission().isEmpty()) {
            completes.add(subcommand.getName());
          }
        }
      }

      return completes;
    }

    return EMPTY_TAB_COMPLETER;
  }

  /**
   * Get a subcommand by the name.
   * @param value The subcommand name.
   * @return The subcommand instance.
   */
  public NeelixSubcommand getSubcommandByName(String value) {
    for(NeelixSubcommand subcommand : this.subcommands) {
      if(subcommand.getName().equalsIgnoreCase(value)) {
        return subcommand;
      }
    }

    return null;
  }
}
