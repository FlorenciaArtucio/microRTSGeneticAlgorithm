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
import com.utils.ParamsView;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import com.genetic.utils.Distance;

public class GeneticAI extends AbstractionLayerAI {
    Random r = new Random();
    private ParamsView P;
    protected UnitTypeTable utt;
    private DoubleSolution solution;
    UnitType resourceType;
    UnitType baseType;
    UnitType barracksType;
    UnitType workerType;
    UnitType lightType;
    UnitType heavyType;
    UnitType rangedType;
    Distance distance = new Distance();

    public GeneticAI(UnitTypeTable a_utt, DoubleSolution solution) {
        super(new AStarPathFinding());
        utt = a_utt;
        resourceType = utt.getUnitType("Resource");
        baseType = utt.getUnitType("Base");
        barracksType = utt.getUnitType("Barracks");
        workerType = utt.getUnitType("Worker");
        lightType = utt.getUnitType("Light");
        heavyType = utt.getUnitType("Heavy");
        rangedType = utt.getUnitType("Ranged");
        this.P = new ParamsView(solution);
        this.solution = solution;
    }

    public void reset() {
        super.reset();
    }

    public AI clone() {
        return new GeneticAI(utt, solution);
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
        if (f < P.getG_AGGR()) {
            Unit closestEnemy = distance.findClosestUnit(u, gs, p, null, -1);
            if (closestEnemy != null) {
                attack(u, closestEnemy);
                return;
            }
        }
        // otherwise, it can either build or harvest
        if (f < P.getG_AGGR() + P.getG_EXPAND()) {
            build(u, resourceType, u.getX(), u.getY());
            return;
        } else {
            Unit closestResource = distance.findClosestUnit(u, gs, p, resourceType, null);
            Unit closestBase = distance.findClosestUnit(u, gs, p, baseType, null);
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
        if (f < P.getWORKER_CAP() && p.getResources() >= workerType.cost) {
            train(u, workerType);
        }
    }

    public void barracksBehavior(Unit u, Player p, GameState gs) {}

    public void lightBehavior(Unit u, Player p, GameState gs) {}

    public void heavyBehavior(Unit u, Player p, GameState gs) {}

    public void rangedBehavior(Unit u, Player p, GameState gs) {}

    @Override
    public List<ParameterSpecification> getParameters()
    {
        List<ParameterSpecification> parameters = new ArrayList<>();
        
        parameters.add(new ParameterSpecification("PathFinding", PathFinding.class, new AStarPathFinding()));

        return parameters;
    }    
}