package com.healthycoderapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private final String environment = "prod";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all UT");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all UT");

    }

    @Nested
    class IsDietRecommendedTests {

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/weight_and_height.csv", numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommended_CSV(Double coderWeight, Double coderHeight) {

            //given
            double weight = coderWeight;
            double height = coderHeight;
            //when
            boolean recommended = BMICalculator.isDietRecommended(coderWeight, coderHeight);
            //then
            assertTrue(recommended);
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {

            //given
            double weight = coderWeight;
            double height = coderHeight;
            //when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);
            //then
            assertTrue(recommended);
        }

        @Test
        void should_ReturnFalse_When_DietNotRecommended() {
            //given
            double weight = 40.0;
            double height = 1.72;
            //when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);
            //then
            assertFalse(recommended);
        }

        @Test
        void should_ThrowArithmeticException_When_HeightZero() {
            //given
            double weight = 40.0;
            double height = 0.0;
            //when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
            //then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    class FindCoderWithWorstTests {

        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            //when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertAll("Hello from AssertAll",
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98, coderWorstBMI.getWeight())
            );
        }

        @Test
        void should_ReturnNull_When_CoderListEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();
            //when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertNull(coderWorstBMI);
        }

        @Test
        void should_ReturnCoderWithWorstBMIIn3Ms_When_CoderListHas10000Elements() {
            //given
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }
            //when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertTimeout(Duration.ofMillis(3), executable);
        }
    }

    @Nested
    @DisplayName("{}{}{} sample inner class display name")
    class GetBMIScoresTests {

        @Test
        @DisplayName(">>>>> sample method display name")
        @DisabledOnOs(OS.MAC)
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expectedScoreArray = {18.52, 29.59, 19.53};
            //when
            double[] actualScoreArray = BMICalculator.getBMIScores(coders);
            //then
            assertArrayEquals(actualScoreArray, expectedScoreArray);
        }
    }
}