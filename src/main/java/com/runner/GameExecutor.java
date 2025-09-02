package com.runner;

import rts.Game;
import com.genetic.GeneticAI;
import ai.abstraction.LightDefense;
import rts.units.UnitTypeTable;
import ai.core.AI;
import rts.GameState;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class GameExecutor {
    private AccessibleGame game;
    DoubleSolution solution;

    public GameExecutor(UnitTypeTable utt, String mapLocation, boolean headless, boolean partiallyObservable, int maxCycles, int updateInterval, DoubleSolution solution) throws Exception {
        GeneticAI ai1 = new GeneticAI(utt, solution);
        AI randomAI = new LightDefense(utt);
        this.game = new AccessibleGame(utt, mapLocation, headless, partiallyObservable, maxCycles, updateInterval, ai1, randomAI);
        this.solution = solution;
    }

    public GameExecutor() throws Exception {
        this(new UnitTypeTable(), "maps/16x16/basesWorkers16x16.xml", true, false, 5000, 20, null);
    }

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
        private DoubleSolution solution;
        public GameResult(int winner, int iterations, DoubleSolution solution) {
            this.winner = winner;
            this.iterations = iterations;
            this.solution = solution;
        }
        
        public int getWinner() { return winner; }
        public int getIterations() { return iterations; }
        public DoubleSolution getSolution() { return solution; }
    }

    public GameResult runGame() {
        try {
            game.start();
            
            GameState finalState = game.getGameState();
            int winner = finalState.winner();
            int iterations = finalState.getTime();
            
            return new GameResult(winner, iterations, solution);
        } catch (Exception e) {
            e.printStackTrace();
            return new GameResult(-1, 0, solution);
        }
    }
}