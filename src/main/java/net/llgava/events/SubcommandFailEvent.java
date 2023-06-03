package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import net.llgava.commands.NeelixSubcommand;
import net.llgava.utils.NeelixSubcommandFailType;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** This event is called when the subcommand failed to be sent. */
public class SubcommandFailEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player playerWithoutPermission;
  @Getter private final NeelixSubcommand subcommand;
  @Getter private final NeelixSubcommandFailType failType;
  @Getter private final Command parent;

  public SubcommandFailEvent(Player playerWithoutPermission, Command parent, NeelixSubcommand subcommand, NeelixSubcommandFailType failType) {
    this.playerWithoutPermission = playerWithoutPermission;
    this.subcommand = subcommand;
    this.failType = failType;
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
