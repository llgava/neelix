package net.llgava.events;

import lombok.Getter;
import lombok.Setter;
import net.llgava.commands.NeelixSubcommand;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWithoutSubcommandPermissionEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  @Getter @Setter private boolean cancelled;
  @Getter private final Player playerWithoutPermission;
  @Getter private final NeelixSubcommand subcommand;
  @Getter private final Command parent;

  /**
   * Triggered when a player run a subcommand without permission for it.
   * @param player The player with no permission for the subcommand.
   * @param parent The parent command.
   * @param subcommand The subcommand.
   */
  public PlayerWithoutSubcommandPermissionEvent(Player player, Command parent, NeelixSubcommand subcommand) {
    this.playerWithoutPermission = player;
    this.subcommand = subcommand;
    this.parent = parent;
  }

  @Override
  public HandlerList getHandlers() { return HANDLERS; }
  public static HandlerList getHandlerList() { return HANDLERS; }
}
