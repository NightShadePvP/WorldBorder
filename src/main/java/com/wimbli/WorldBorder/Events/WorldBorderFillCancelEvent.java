package com.wimbli.WorldBorder.Events;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Blok on 12/20/2018.
 */
public class WorldBorderFillCancelEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private World world;

    public WorldBorderFillCancelEvent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
