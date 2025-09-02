package com.runner;

import rts.Game;
import com.genetic.GeneticAI;
import ai.RandomAI;
import rts.units.UnitTypeTable;
import ai.core.AI;
import rts.GameState;

public class GameExecutor {
    private AccessibleGame game;
    private double agression;
    private double expansion;
    private double workForce;

    public static class AccessibleGame extends Game {
        public AccessibleGame(UnitTypeTable utt, String mapLocation, boolean headless, 
                            boolean partiallyObservable, int maxCycles, int updateInterval, 
                            AI ai1, AI ai2) throws Exception {
            super(utt, mapLocation, headless, partiallyObservable, maxCycles, updateInterval, ai1, ai2);
        }
        
        public GameState getGameState() {
            return gs;
        }
    }

    public static class GameResult {
        private int winner;
        private int iterations;
        private double agression;
        private double expansion;
        private double workForce;
        public GameResult(int winner, int iterations, double agression, double expansion, double workForce) {
            this.winner = winner;
            this.iterations = iterations;
            this.agression = agression;
            this.expansion = expansion;
            this.workForce = workForce;
        }
        
        public int getWinner() { return winner; }
        public int getIterations() { return iterations; }
        public double getAgression() { return agression; }
        public double getExpansion() { return expansion; }
        public double getWorkForce() { return workForce; }
    }

    public GameExecutor(UnitTypeTable utt, String mapLocation, boolean headless, boolean partiallyObservable, int maxCycles, int updateInterval, double agression, double expansion, double workForce) throws Exception {
        GeneticAI ai1 = new GeneticAI(utt, agression, expansion, workForce);
        AI randomAI = new RandomAI(utt);
        this.game = new AccessibleGame(utt, mapLocation, headless, partiallyObservable, maxCycles, updateInterval, ai1, randomAI);
        this.agression = agression;
        this.expansion = expansion;
        this.workForce = workForce;
    }

    public GameExecutor() throws Exception {
        this(new UnitTypeTable(), "maps/16x16/basesWorkers16x16.xml", true, false, 5000, 20, 0.5, 0.5, 0.5);
    }

    public GameResult runGame() {
        try {
            game.start();
            
            GameState finalState = game.getGameState();
            int winner = finalState.winner();
            int iterations = finalState.getTime();
            
            return new GameResult(winner, iterations, agression, expansion, workForce);
        } catch (Exception e) {
            e.printStackTrace();
            return new GameResult(-1, 0, agression, expansion, workForce);
        }
    }
}