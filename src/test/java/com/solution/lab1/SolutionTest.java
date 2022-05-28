package com.solution.lab1;

import com.solution.lab1.Services.SolutionService;
import com.solution.lab1.entity.SolutionParameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
    private final SolutionService TestSol = new SolutionService();
    @Test
    void PerformSolution() {
        int firstValue = -75800;
        int secondValue = 65536;
        int lowerInterval = 100000;
        int higherInterval = 200000;
        int exceptedResult = 141336;
        SolutionParameters params = new SolutionParameters(firstValue, secondValue, lowerInterval, higherInterval);
        assertEquals(exceptedResult,TestSol.PerformCalculation(params));
    }
    @Test
    void PerformWrongSolution() {
        int firstValue = -75800;
        int secondValue = 65536;
        int lowerInterval = 100000;
        int higherInterval = 140000;
        int exceptedResult = 141336;
        SolutionParameters params = new SolutionParameters(firstValue, secondValue, lowerInterval, higherInterval);
        assertEquals(exceptedResult,TestSol.PerformCalculation(params));
    }
}
