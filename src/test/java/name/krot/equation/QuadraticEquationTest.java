package name.krot.equation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


class QuadraticEquationTest {

    @Test
    @DisplayName("Для уравнения x^2 + 1 = 0 корней нет (возвращается пустой массив)")
    void testNoRoots() {
        List<Double> solve = QuadraticEquation.solve(1, 0, 1);
        System.out.println(solve);
        Assertions.assertTrue(solve.isEmpty());
    }

    @Test
    @DisplayName("Для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)")
    void testSingleRootMultiplicityOne() {
        List<Double> solve = QuadraticEquation.solve(1, 0, -1);
        Assertions.assertEquals(List.of(1.0, -1.0), solve);
    }

    @Test
    @DisplayName("Для уравнения x^2 + 2x + 1 = 0 есть один корень кратности 2 (x1= x2 = -1)")
    void testSingleRootMultiplicityTwo() {
        List<Double> solve = QuadraticEquation.solve(1, 2, 1);
        Assertions.assertEquals(List.of(-1.0, -1.0), solve);
    }

    @Test
    @DisplayName("Коэффициент a не может быть равен 0 (выбрасывается исключение)")
    void testCoefficientANotZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquation.solve(0, 1, 1);
        });
    }

    @Test
    @DisplayName("Для уравнения x^2 + 2.0000000001x + 1.0000000001 = 0 есть один корень кратности 2 (x1= x2 = -1)")
    void testSingleRootMultiplicityTwo2() {
        List<Double> solve = QuadraticEquation.solve(1, 2.0000000001, 1.0000000001);
        Assertions.assertEquals(List.of(-1.0, -1.0), solve);
    }

    @Test
    @DisplayName("Коэффициенты не могут быть NaN (выбрасывается исключение)")
    void testCoefficientsNotNaN() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(Double.NaN, 1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(1, Double.NaN, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> QuadraticEquation.solve(1, 1, Double.NaN));
    }

    @Test
    @DisplayName("Коэффициенты не могут быть бесконечными (выбрасывается исключение)")
    void testCoefficientsNotInfinity() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(Double.POSITIVE_INFINITY, 1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(Double.NEGATIVE_INFINITY, 1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(1, Double.POSITIVE_INFINITY, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(1, Double.NEGATIVE_INFINITY, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(1, 1, Double.POSITIVE_INFINITY));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                QuadraticEquation.solve(1, 1, Double.NEGATIVE_INFINITY));
    }
}
