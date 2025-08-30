package com.genetic;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class MicroRTSFitness {
    public double evaluateSolution(DoubleSolution solution) {
        double agression = solution.variables().get(0);
        double expansion = solution.variables().get(1);
        
        // TODO calculate fitness score
        return 0.5;
        
    }
}