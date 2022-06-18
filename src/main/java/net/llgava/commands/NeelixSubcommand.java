package net.llgava.commands;


import lombok.Getter;
import net.llgava.utils.NeelixMessages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class NeelixSubcommand {
  @Getter private final String name;
  @Getter private final String permission;

  public NeelixSubcommand(String name, String permission) {
    this.name = name;
    this.permission = Objects.requireNonNull(permission, NeelixMessages.SUBCOMMAND_PERMISSION_CANNOT_BE_NULL.getMessage());
  }

  public abstract void execute(Player player, String[] args);

  public CommandExecutor toCommandExecutor() {
    return (sender, command, label, args) -> {
      this.execute((Player) sender, args);
      return true;
    };
  }
}
