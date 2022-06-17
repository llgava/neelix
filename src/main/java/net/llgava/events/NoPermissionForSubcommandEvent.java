package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NoPermissionForSubcommandEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player playerWithoutPermission;

  public NoPermissionForSubcommandEvent(Player playerWithoutPermission) {
    this.playerWithoutPermission = playerWithoutPermission;
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}
