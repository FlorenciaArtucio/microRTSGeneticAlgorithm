package com.genetic;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import com.genetic.MicroRTSFitness;
import java.util.List;
import java.util.Arrays;

public class MicroRTSProblem extends AbstractDoubleProblem {
    private int instances;
    private MicroRTSFitness fitnessEvaluator;

    public MicroRTSProblem(int instances) {
        this.instances = instances;
        numberOfObjectives(1);
        numberOfConstraints(0);
        name("MicroRTSProblem");

        List<Double> lowerLimit = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> upperLimit = Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 30.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
       variableBounds(lowerLimit, upperLimit);

        this.fitnessEvaluator = new MicroRTSFitness();
    }

    @Override
    public int numberOfVariables() {
        return 16;
    }

    @Override
    public DoubleSolution createSolution() {
        return new DefaultDoubleSolution(variableBounds(), numberOfObjectives(), numberOfConstraints());
    }

    @Override
    public DoubleSolution evaluate(DoubleSolution solution) {
        double fitness = this.fitnessEvaluator.evaluateSolution(solution, instances);

        solution.objectives()[0] = fitness;

        return solution;
    }

}