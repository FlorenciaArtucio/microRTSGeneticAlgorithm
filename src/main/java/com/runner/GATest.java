package com.runner;

import com.genetic.MicroRTSProblem;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

import java.util.List;

public class GATest {
    public static void main(String[] args) {
        MicroRTSProblem problem = new MicroRTSProblem(1);

        double crossoverProbability = 0.8;
        double distributionIndex = 20;
        SBXCrossover crossover = new SBXCrossover(crossoverProbability, distributionIndex);

        double mutationProbability = 1.0 / problem.numberOfVariables();
        double mutationDistributionIndex = 20;
        PolynomialMutation mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex);

        int populationSize = 100;
        int offspringPopulationSize = populationSize;
        int matingPoolSize = populationSize;

        int maximumEvaluations = 7500;

        BinaryTournamentSelection<DoubleSolution> selection = new BinaryTournamentSelection<>();

        NSGAIIBuilder<DoubleSolution> builder = new NSGAIIBuilder<>(problem, crossover, mutation, populationSize);
        builder.setSelectionOperator(selection);
        builder.setSolutionListEvaluator(new SequentialSolutionListEvaluator<>());
        builder.setMatingPoolSize(matingPoolSize);
        builder.setOffspringPopulationSize(offspringPopulationSize);
        builder.setMaxEvaluations(maximumEvaluations);

        NSGAII<DoubleSolution> algorithm = builder.build();

        algorithm.run();

        List<DoubleSolution> population = algorithm.result();
        System.out.println("\n=== FINAL RESULTS ===");
        System.out.println("Final population size: " + population.size());

        System.out.println("\nBest solutions:");
        for (int i = 0; i < Math.min(5, population.size()); i++) {
            DoubleSolution solution = population.get(i);
            System.out.println("Solution " + (i+1) + ": " + solution.variables() +
                    " | Fitness: " + solution.objectives()[0]);
        }
    }
}
