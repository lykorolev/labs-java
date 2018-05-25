import java.lang.System;
import java.lang.Math;

public class Laba_1 {
    public static void main(String[] args) {

        if (args.length != 3) {
            throw new RuntimeException("\nвведите 3 числа");
        }
        int a, b, h;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            h = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("\nчисла должны быть целыми");
        }
        if (a >= b) {
            throw new RuntimeException("\nвторое число должно быть больше, чем первое");
        }
        System.out.print("x  .........  F(x)\n");
        for (int i = a; i <= b; i += h) {
            System.out.print( i + "  .........  " + (Math.tan(2 * i) - 3) + "\n");
        }
    }
}