package com.genetic.utils;

import rts.GameState;
import rts.PhysicalGameState;
import rts.units.Unit;
import rts.Player;
import rts.units.UnitType;
import java.lang.Math;

public class Distance {
    public Unit findClosestUnit(Unit u, GameState gs, Player p, UnitType type, Integer Targetplayer) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        Unit closestResource = null;
        int closestDistance = 0;
        for (Unit u2 : pgs.getUnits()) {
            if (type == null || u2.getType() == type) {
                if (Targetplayer != null && u2.getPlayer() != Targetplayer) continue;
                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());
                if (closestResource == null || d < closestDistance) {
                    closestResource = u2;
                    closestDistance = d;
                }
            }
        }
        return closestResource;
    }
}