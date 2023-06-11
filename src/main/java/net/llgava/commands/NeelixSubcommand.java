package net.llgava.commands;

import lombok.Getter;
import net.llgava.utils.NeelixMessages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class NeelixSubcommand {
  @Getter private final String name;
  @Getter private final String permission;

  /**
   * A new subcommand instance.
   * @param name The subcommand name.
   * @param permission The needed permission to use the subcommand.
   * */
  public NeelixSubcommand(String name, String permission) {
    this.name = name;
    this.permission = Objects.requireNonNull(permission, NeelixMessages.SUBCOMMAND_PERMISSION_CANNOT_BE_NULL.getRawMessage());
  }

  /**
   * Code block to be executed when the subcommand is sent by the player
   * */
  public abstract void execute(Player player, String[] args);

  /**
   * Convert the {@link NeelixSubcommand} to a {@link CommandExecutor}. <br />
   * This can be used to create aliases of subcommands.
   * */
  public CommandExecutor toCommandExecutor() {
    return (sender, command, label, args) -> {
      if (!(sender instanceof Player)) return true;

      this.execute((Player) sender, args);
      return true;
    };
  }
}
