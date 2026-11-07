package edu.course.lab03;

// Базовая часть: интерфейс и две реализации политики скидок.
interface DiscountPolicy {
    double apply(double price);
}

class NoDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        validatePrice(price);
        return price;
    }

    private void validatePrice(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException(
                    "Цена должна быть конечным неотрицательным числом"
            );
        }
    }
}

class PercentDiscount implements DiscountPolicy {

    private final double percent;

    public PercentDiscount(double percent) {
        if (!Double.isFinite(percent) || percent < 0 || percent > 100) {
            throw new IllegalArgumentException(
                    "Процент скидки должен находиться в диапазоне от 0 до 100"
            );
        }

        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException(
                    "Цена должна быть конечным неотрицательным числом"
            );
        }

        return price * (1 - percent / 100.0);
    }
}

public class PriceCalculator {

    private final DiscountPolicy discountPolicy;

    public PriceCalculator(DiscountPolicy discountPolicy) {
        if (discountPolicy == null) {
            throw new IllegalArgumentException(
                    "Политика скидки не может быть null"
            );
        }

        this.discountPolicy = discountPolicy;
    }

    public double calculate(double price) {
        return discountPolicy.apply(price);
    }
}


// Часть по инженерии систем ИИ: интерфейс и две стратегии нормализации.
interface NormalizationStrategy {
    double[] normalize(double[] values);
}

class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        validateValues(values);

        double min = values[0];
        double max = values[0];

        // минимум и максимум.
        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }

            if (values[i] > max) {
                max = values[i];
            }
        }

        double[] result = new double[values.length];

        if (max == min) {
            return result; // Для одинаковых значений возвращаем массив из нулей.
        }

        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / (max - min);
        }

        return result;
    }

    private void validateValues(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Массив не должен быть null или пустым"
            );
        }

        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Массив должен содержать только конечные числа"
                );
            }
        }
    }
}

class MeanCenteringNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        validateValues(values);

        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        double mean = sum / values.length;
        double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - mean;
        }

        return result;
    }

    private void validateValues(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Массив не должен быть null или пустым"
            );
        }

        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Массив должен содержать только конечные числа"
                );
            }
        }
    }
}

class FeatureProcessor {

    private final NormalizationStrategy strategy;

    public FeatureProcessor(NormalizationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Стратегия нормализации не может быть null"
            );
        }

        this.strategy = strategy;
    }

    public double[] process(double[] values) {
        return strategy.normalize(values);
    }
}