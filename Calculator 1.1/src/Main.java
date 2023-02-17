import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = (int) calc.plus.apply(1, 2);
        int b = (int) calc.minus.apply(1, 1);
//        int c = (int) calc.devide.apply(a, b); так было до написания блока с исключением
//        на этом этапе мы пытались поделить на ноль, т.к. b = 0. Возникала ошибка
        int c = calc.devide(a, b);
        calc.println.accept(c);
    }
}

class Calculator<T> {
    static Supplier<Calculator> instance = Calculator::new;

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    //    BinaryOperator<Integer> devide = (x, y) -> x / y; так было до написания блока с исключением
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;

    public Integer devide(int x, int y) throws ArithmeticException {
        try {
            int q = x / y;
        } catch (ArithmeticException w) {
            System.out.println(w.getMessage());
        } finally {
            System.out.println("Делить на ноль нельзя");
        }
        if (y == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return null;
    }

}


