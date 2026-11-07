package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lab03Test {

    // Тесты базовой части: скидки.

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0));
    }

    @Test
    void noDiscountAcceptsZeroPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(0.0, policy.apply(0.0));
    }

    @Test
    void percentDiscountCalculatesTwentyPercent() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0));
    }

    @Test
    void percentDiscountOfOneHundredReturnsZero() {
        DiscountPolicy policy = new PercentDiscount(100);

        assertEquals(0.0, policy.apply(100.0));
    }

    @Test
    void priceCalculatorCanUseDifferentPolicies() {
        PriceCalculator calculator =
                new PriceCalculator(new NoDiscount());

        assertEquals(100.0, calculator.calculate(100.0));

        calculator = new PriceCalculator(new PercentDiscount(20));

        assertEquals(80.0, calculator.calculate(100.0));
    }

    @Test
    void negativePriceIsRejected() {
        DiscountPolicy policy = new NoDiscount();

        assertThrows(
                IllegalArgumentException.class,
                () -> policy.apply(-1.0)
        );
    }

    @Test
    void invalidDiscountPercentIsRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PercentDiscount(120)
        );
    }


    // Тесты инженерии систем ИИ: нормализация.

    @Test
    void minMaxNormalizationWorksCorrectly() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        double[] result = strategy.normalize(
                new double[]{10, 20, 30, 40, 50}
        );

        assertArrayEquals(
                new double[]{0, 0.25, 0.5, 0.75, 1},
                result,
                0.000001
        );
    }

    @Test
    void minMaxNormalizationDoesNotChangeOriginalArray() {
        double[] values = {10, 20, 30};
        double[] original = values.clone();

        NormalizationStrategy strategy = new MinMaxNormalization();
        strategy.normalize(values);

        assertArrayEquals(original, values);
        }

    @Test
    void minMaxNormalizationHandlesEqualValues() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        assertArrayEquals(
                new double[]{0, 0, 0},
                strategy.normalize(new double[]{5, 5, 5}),
                0.000001
        );
    }

    @Test
    void meanCenteringWorksCorrectly() {
        NormalizationStrategy strategy =
                new MeanCenteringNormalization();

        assertArrayEquals(
                new double[]{-20, -10, 0, 10, 20},
                strategy.normalize(new double[]{10, 20, 30, 40, 50}),
                0.000001
        );
    }

    @Test
    void meanCenteringDoesNotChangeOriginalArray() {
        double[] values = {10, 20, 30};
        double[] original = values.clone();

        NormalizationStrategy strategy =
                new MeanCenteringNormalization();
        strategy.normalize(values);
        assertArrayEquals(original, values);
    }

    @Test
    void meanCenteringWorksWithNegativeNumbers() {
        NormalizationStrategy strategy =
                new MeanCenteringNormalization();

        assertArrayEquals(
                new double[]{-10, 0, 10},
                strategy.normalize(new double[]{-10, 0, 10}),
                0.000001
        );
    }

    @Test
    void featureProcessorCanReplaceStrategy() {
        double[] values = {10, 20, 30, 40, 50};

        FeatureProcessor processor =
                new FeatureProcessor(new MinMaxNormalization());

        assertArrayEquals(
                new double[]{0, 0.25, 0.5, 0.75, 1},
                processor.process(values),
                0.000001
        );

        processor = new FeatureProcessor(
                new MeanCenteringNormalization()
        );

        assertArrayEquals(
                new double[]{-20, -10, 0, 10, 20},
                processor.process(values),
                0.000001
        );
    }

    @Test
    void emptyArrayIsRejected() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        assertThrows(
                IllegalArgumentException.class,
                () -> strategy.normalize(new double[]{})
        );
    }
}