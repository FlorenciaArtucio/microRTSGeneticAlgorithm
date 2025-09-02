package com.utils;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import java.util.List;

public class ExportSolution {
    public void exportSolution(DoubleSolution solution, String filename) {
        ParamsView P = new ParamsView(solution);
        P.printParams();
        System.out.println("--------------------------------");
        System.out.println("Fitness: " + solution.objectives()[0]);
        SolutionListOutput output = new SolutionListOutput(List.of(solution));
        output.printVariablesToFile(filename);
    }
}