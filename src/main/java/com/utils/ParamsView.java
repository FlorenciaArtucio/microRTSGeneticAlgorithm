package com.utils;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public final class ParamsView {
    private final DoubleSolution s;

    public ParamsView(DoubleSolution s) { this.s = s; }

    private static final int G_AGGR = 0; // Agression [0, 1]
    private static final int G_EXPAND = 1; // Expansion [0, 1]
    private static final int G_ECON = 2; // Economy [0, 1]
    private static final int THR_HP_LOW = 3; // Threshold for low HP [0, 1]
    private static final int THR_POWER_ADV = 4; // Threshold for power advantage [0, 1]
    private static final int THR_DISTANCE_ENGAGE = 5; // Threshold for distance engage [0, 1]
    private static final int WORKER_CAP = 6; // Worker cap [4, 30]
    private static final int T_BARRACKS_TIME = 7; // Time to build a barracks [0, 1]
    private static final int RANGED_RATIO = 8; // Ranged ratio [0, 1]
    private static final int EXPAND_TRIGGER_TIME = 9; // Expand trigger time [0, 1]
    private static final int W_ATTACK = 10; // Worker attack [0, 1]
    private static final int W_HARASS = 11; // Worker harass [0, 1]
    private static final int W_DEFEND = 12; // Worker defend [0, 1]
    private static final int W_RETREAT = 13; // Worker retreat [0, 1]
    private static final int W_HARVEST = 14; // Worker harvest [0, 1]
    private static final int W_BUILD = 15;  

    public double getG_AGGR() { return s.variables().get(G_AGGR); }
    public double getG_EXPAND() { return s.variables().get(G_EXPAND); }
    public double getG_ECON() { return s.variables().get(G_ECON); }
    public double getTHR_HP_LOW() { return s.variables().get(THR_HP_LOW); }
    public double getTHR_POWER_ADV() { return s.variables().get(THR_POWER_ADV); }
    public double getTHR_DISTANCE_ENGAGE() { return s.variables().get(THR_DISTANCE_ENGAGE); }
    public double getWORKER_CAP() { return s.variables().get(WORKER_CAP); }
    public double getT_BARRACKS_TIME() { return s.variables().get(T_BARRACKS_TIME); }
    public double getRANGED_RATIO() { return s.variables().get(RANGED_RATIO); }
    public double getEXPAND_TRIGGER_TIME() { return s.variables().get(EXPAND_TRIGGER_TIME); }
    public double getW_ATTACK() { return s.variables().get(W_ATTACK); }
    public double getW_HARASS() { return s.variables().get(W_HARASS); }
    public double getW_DEFEND() { return s.variables().get(W_DEFEND); }
    public double getW_RETREAT() { return s.variables().get(W_RETREAT); }
    public double getW_HARVEST() { return s.variables().get(W_HARVEST); }
    public double getW_BUILD() { return s.variables().get(W_BUILD); }

    public void printParams() {
        System.out.println("G_AGGR: " + getG_AGGR());
        System.out.println("G_EXPAND: " + getG_EXPAND());
        System.out.println("G_ECON: " + getG_ECON());
        System.out.println("THR_HP_LOW: " + getTHR_HP_LOW());
        System.out.println("THR_POWER_ADV: " + getTHR_POWER_ADV());
        System.out.println("THR_DISTANCE_ENGAGE: " + getTHR_DISTANCE_ENGAGE());
        System.out.println("WORKER_CAP: " + getWORKER_CAP());
        System.out.println("T_BARRACKS_TIME: " + getT_BARRACKS_TIME());
        System.out.println("RANGED_RATIO: " + getRANGED_RATIO());
        System.out.println("EXPAND_TRIGGER_TIME: " + getEXPAND_TRIGGER_TIME());
        System.out.println("W_ATTACK: " + getW_ATTACK());
        System.out.println("W_HARASS: " + getW_HARASS());
        System.out.println("W_DEFEND: " + getW_DEFEND());
        System.out.println("W_RETREAT: " + getW_RETREAT());
        System.out.println("W_HARVEST: " + getW_HARVEST());
        System.out.println("W_BUILD: " + getW_BUILD());
    }
}