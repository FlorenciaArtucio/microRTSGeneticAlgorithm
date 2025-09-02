package com.genetic;

import ai.abstraction.AbstractionLayerAI;
import rts.units.UnitTypeTable;
import rts.units.UnitType;
import rts.units.Unit;
import rts.Player;
import rts.GameState;
import rts.PlayerAction;
import ai.core.AI;
import ai.abstraction.pathfinding.AStarPathFinding;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import ai.abstraction.pathfinding.PathFinding;
import ai.core.ParameterSpecification;
import rts.PhysicalGameState;

public class GeneticAI extends AbstractionLayerAI {
    Random r = new Random();
    private double agression;
    private double expansion;
    private double workForce;
    protected UnitTypeTable utt;
    UnitType resourceType;
    UnitType baseType;
    UnitType barracksType;
    UnitType workerType;
    UnitType lightType;
    UnitType heavyType;
    UnitType rangedType;

    public GeneticAI(UnitTypeTable a_utt, double agression, double expansion, double workForce) {
        super(new AStarPathFinding());
        utt = a_utt;
        resourceType = utt.getUnitType("Resource");
        baseType = utt.getUnitType("Base");
        barracksType = utt.getUnitType("Barracks");
        workerType = utt.getUnitType("Worker");
        lightType = utt.getUnitType("Light");
        heavyType = utt.getUnitType("Heavy");
        rangedType = utt.getUnitType("Ranged");
        this.agression = agression;
        this.expansion = expansion;
        this.workForce = workForce;
    }

    public void reset() {
        super.reset();
    }

    public AI clone() {
        return new GeneticAI(utt, agression, expansion, workForce);
    }

    public PlayerAction getAction(int player, GameState gs) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        PlayerAction pa = new PlayerAction();
        Player p = gs.getPlayer(player);

        if (!gs.canExecuteAnyAction(player)) return pa;

        for (Unit u : pgs.getUnits()) {
            if (u.getPlayer() == player) {
                unitBehavior(u, p, gs);
            }
        }

        return translateActions(player, gs);
    }   

    public void unitBehavior(Unit u, Player p, GameState gs) {
        UnitType unitType = u.getType();
        
        if (unitType == workerType) {
            workerBehavior(u, p, gs);
        } 
        if (unitType == baseType) {
            baseBehavior(u, p, gs);
        } 
        if (unitType == barracksType) {
            barracksBehavior(u, p, gs);
        } 
        if (unitType == lightType) {
            lightBehavior(u, p, gs);
        }
        if (unitType == heavyType) {
            heavyBehavior(u, p, gs);
        }
        if (unitType == rangedType) {
            rangedBehavior(u, p, gs);
        }
    }

    public void workerBehavior(Unit u, Player p, GameState gs) {
        float f = r.nextFloat();
        if (f < agression) {
            Unit closestEnemy = findClosestEnemy(u, gs, p);
            if (closestEnemy != null) {
                attack(u, closestEnemy);
                return;
            }
        }
        // otherwise, it can either build or harvest
        if (f < agression + expansion) {
            build(u, resourceType, u.getX(), u.getY());
            return;
        } else {
            Unit closestResource = findClosestResource(u, gs, p);
            Unit closestBase = findClosestBase(u, gs, p);
            harvest(u, closestResource, closestBase);
            return;
        }
    }

    public void baseBehavior(Unit u, Player p, GameState gs) {
        int nworkers = 0;
        float f = r.nextFloat();
        for (Unit u2 : gs.getPhysicalGameState().getUnits()) {
            if (u2.getType() == workerType
                    && u2.getPlayer() == p.getID()) {
                nworkers++;
            }
        }
        if (f < workForce && p.getResources() >= workerType.cost) {
            train(u, workerType);
        }
    }

    public void barracksBehavior(Unit u, Player p, GameState gs) {}

    public void lightBehavior(Unit u, Player p, GameState gs) {}

    public void heavyBehavior(Unit u, Player p, GameState gs) {}

    public void rangedBehavior(Unit u, Player p, GameState gs) {}

    private Unit findClosestEnemy(Unit u, GameState gs, Player p) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        Unit closestEnemy = null;
        int closestDistance = 0;
        for (Unit u2 : pgs.getUnits()) {
            if (u2.getPlayer() >= 0 && u2.getPlayer() != p.getID()) {
                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());
                if (closestEnemy == null || d < closestDistance) {
                    closestEnemy = u2;
                    closestDistance = d;
                }
            }
        }
        return closestEnemy;
    }

    private Unit findClosestResource(Unit u, GameState gs, Player p) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        Unit closestResource = null;
        int closestDistance = 0;
        for (Unit u2 : pgs.getUnits()) {
            if (u2.getType().isResource) {
                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());
                if (closestResource == null || d < closestDistance) {
                    closestResource = u2;
                    closestDistance = d;
                }
            }
        }
        return closestResource;
    }

    private Unit findClosestBase(Unit u, GameState gs, Player p) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        Unit closestBase = null;
        int closestDistance = 0;
        for (Unit u2 : pgs.getUnits()) {
            if (u2.getType() == baseType && u2.getPlayer() != p.getID()) {
                int d = Math.abs(u2.getX() - u.getX()) + Math.abs(u2.getY() - u.getY());

                if (closestBase == null || d < closestDistance) {
                    closestBase = u2;
                    closestDistance = d;
                }
            }
        }
        return closestBase;
    }

    @Override
    public List<ParameterSpecification> getParameters()
    {
        List<ParameterSpecification> parameters = new ArrayList<>();
        
        parameters.add(new ParameterSpecification("PathFinding", PathFinding.class, new AStarPathFinding()));

        return parameters;
    }    
}