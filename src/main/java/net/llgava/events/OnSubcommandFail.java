package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import net.llgava.commands.NeelixSubcommand;
import net.llgava.utils.SubcommandFailType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnSubcommandFail extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player playerWithoutPermission;
  @Getter private final NeelixSubcommand subcommand;
  @Getter private final SubcommandFailType failType;

  public OnSubcommandFail(Player playerWithoutPermission, NeelixSubcommand subcommand, SubcommandFailType errorType) {
    this.playerWithoutPermission = playerWithoutPermission;
    this.subcommand = subcommand;
    this.failType = errorType;
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}
