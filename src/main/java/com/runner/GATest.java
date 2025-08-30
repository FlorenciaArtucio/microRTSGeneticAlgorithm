package com.runner;

import com.genetic.MicroRTSProblem;
import com.genetic.MicroRTSSolution;

public class GATest {
    public static void main(String[] args) {
        MicroRTSProblem problem = new MicroRTSProblem();
        
        // Use the problem's createSolution method instead
        MicroRTSSolution solution = (MicroRTSSolution) problem.createSolution();

        // Fix method names to match AbstractDoubleProblem
        System.out.println("Problem: " + problem.name());
        System.out.println("Variables: " + problem.numberOfVariables());
        System.out.println("Objectives: " + problem.numberOfObjectives());
        System.out.println("Constraints: " + problem.numberOfConstraints());

        problem.evaluate(solution);
        System.out.println("Score: " + solution.objectives()[0]);
    }
}