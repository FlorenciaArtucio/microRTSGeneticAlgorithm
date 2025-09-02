package com.genetic;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import com.runner.GameExecutor;
import com.runner.GameExecutor.GameResult;
import rts.units.UnitTypeTable;
import java.lang.Math;

public class MicroRTSFitness {
    public double evaluateSolution(DoubleSolution solution, int instances) {
        try {

            // System.out.println("Agression: " + agression + " Expansion: " + expansion + " Work force: " + workForce);
            double totalScore = 0;

            for (int i = 0; i < instances; i++) {
                GameExecutor gameExecutor = new GameExecutor(new UnitTypeTable(), "maps/16x16/basesWorkers16x16.xml", true, false, 5000, 20, solution);
                GameResult gameResult = gameExecutor.runGame();
                if (gameResult.getWinner() == -1) {
                    continue;
                }
                totalScore += gameResult.getWinner();
            }
        
        // winrate
        System.out.println("Win rate: " + ((instances - totalScore) / instances));
        return totalScore / instances; 
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }
}