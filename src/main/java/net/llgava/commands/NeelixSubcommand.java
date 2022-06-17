package net.llgava.commands;


import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class NeelixSubcommand {
  @Getter private final String name;
  @Getter private final String permission;

  public NeelixSubcommand(String name, String permission) {
    this.name = name;
    this.permission = Objects.requireNonNull(permission, "Permission cannot be null, use empty string instead.");
  }

  public abstract void execute(Player player, String[] args);

  public CommandExecutor toCommandExecutor() {
    return (sender, command, label, args) -> {
      this.execute((Player) sender, args);
      return true;
    };
  }
}
