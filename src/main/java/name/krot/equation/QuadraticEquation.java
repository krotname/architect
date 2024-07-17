package name.krot.equation;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


/**
 * ТЗ - Научиться писать модульные тесты по TDD, так как модульные тесты является важной частью концепции Time To Market и
 * часто применяется в современных проектах.
 * <p>
 * Настроить CI, чтобы можно было собирать проект и прогонять тесты.
 * Необходимо реализовать операцию нахождения квадратного уравнения. Предположим, что эта операция описывается следующей
 * функцией c поправкой на конкретный язык программирования. В ООП языках эта функция реализуется в виде метода класса.
 * solve(double a, double b, double c): double[]
 * здесь a, b, c - коэффициенты квадратного уравнения, функция возвращает список корней квадратного уравнения.
 * Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
 * Написать минимальную реализацию функции solve, которая удовлетворяет данному тесту.
 * Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
 * Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.5.
 * Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1).
 * Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.7.
 * Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
 * Примечание. Учесть, что a имеет тип double и сравнивать с 0 через == нельзя.
 * Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.9.
 * С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства, подобрать такие коэффициенты квадратного
 * уравнения для случая одного корня кратности два, чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон.
 * Эти коэффициенты должны заменить коэффициенты в тесте из п. 7.
 * При необходимости поправить реализацию квадратного уравнения.
 * Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест с их использованием на
 * все коэффициенты. solve должен выбрасывать исключение.
 * Написать минимальную реализацию функции solve, которая удовлетворяет тесту из п.13.
 * Сделать merge request/pull request и ссылку на него указать при сдаче ДЗ.
 */

@UtilityClass
public class QuadraticEquation {

    public static List<Double> solve(double a, double b, double c) {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)) {
            throw new IllegalArgumentException("Coefficients cannot be NaN.");
        }

        if (Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
            throw new IllegalArgumentException("Coefficients cannot be infinite.");
        }

        double epsilon = 1e-10;

        if (Math.abs(a) < epsilon) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero.");
        }

        List<Double> roots = new ArrayList<>();

        double discriminant = b * b - 4 * Math.abs(a) * c;

        if (discriminant > epsilon) {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDiscriminant) / (2 * a);
            double x2 = (-b - sqrtDiscriminant) / (2 * a);
            roots.add(x1);
            roots.add(x2);
        } else if (Math.abs(discriminant) <= epsilon) {
            double root = -b / (2 * a);
            roots.add(Math.ceil(root));
            roots.add(Math.ceil(root));
        }

        return roots;
    }
}
