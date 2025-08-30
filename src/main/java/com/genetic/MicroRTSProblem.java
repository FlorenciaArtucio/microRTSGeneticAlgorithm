package com.genetic;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import com.genetic.MicroRTSSolution;
import com.genetic.MicroRTSFitness;
import java.util.List;
import java.util.Arrays;

public class MicroRTSProblem extends AbstractDoubleProblem {

    private MicroRTSFitness fitnessEvaluator;

    public MicroRTSProblem() {
        numberOfObjectives(1);
        numberOfConstraints(0);
        name("MicroRTSProblem");

        List<Double> lowerLimit = Arrays.asList(0.0, 0.0);
        List<Double> upperLimit = Arrays.asList(1.0, 1.0);
        variableBounds(lowerLimit, upperLimit);

        this.fitnessEvaluator = new MicroRTSFitness();
    }

    @Override
    public DoubleSolution createSolution() {
        return new MicroRTSSolution(variableBounds(), numberOfObjectives(), numberOfConstraints());
    }

    @Override
    public DoubleSolution evaluate(DoubleSolution solution) {
        double fitness = this.fitnessEvaluator.evaluateSolution(solution);

        double score = fitness;
        solution.objectives()[0] = score;

        return solution;
    }

}