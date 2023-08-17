package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SubcommandNotFoundEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player player;
  @Getter private final Command parent;

  /**
   * Triggered when a player executes a subcommand that doesn't exist.
   * @param player The player who executed the invalid subcommand.
   */
  public SubcommandNotFoundEvent(Player player, Command parent) {
    this.player = player;
    this.parent = parent;
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}
