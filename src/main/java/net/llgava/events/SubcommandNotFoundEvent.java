package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** This event is called when the subcommand don't exist. */
public class SubcommandNotFoundEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player player;

  public SubcommandNotFoundEvent(Player player) {
    this.player = player;
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}