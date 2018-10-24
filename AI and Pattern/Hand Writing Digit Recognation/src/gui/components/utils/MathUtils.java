package gui.components.utils;

/**
 * @author Bayu Dwiyan Satria
 */
public final class MathUtils {

    public static double logistic(double x) {
        return 1.0D / (1.0D + Math.pow(Math.E, -x));
    }

    public static double dLogistic(double x) {
        double image = logistic(x);
        return image * (1.0D - image);
    }

    public static <T extends Comparable<T>> T clamp(T min, T max, T value) {
        if (value.compareTo(min) < 0) {
            return min;
        }
        if (value.compareTo(max) > 0) {
            return max;
        }
        return value;
    }
}
