package com.genetic;

import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import java.util.List;
import org.uma.jmetal.util.bounds.Bounds;

public class MicroRTSSolution extends DefaultDoubleSolution {
    private double agression;
    private double expansion;
    private double workForce;

    public MicroRTSSolution(List<Bounds<Double>> bounds, int numberOfObjectives, int numberOfConstraints) {
        super(bounds, numberOfObjectives, numberOfConstraints);
    }

    public double getAgression() {
        return this.agression;
    }

    public double getExpansion() {
        return this.expansion;
    }

    public double getWorkForce() {
        return this.workForce;
    }

    public void setAgression(double agression) {
        this.agression = agression;
    }

    public void setExpansion(double expansion) {
        this.expansion = expansion;
    }

    public void setWorkForce(double workForce) {
        this.workForce = workForce;
    }
}