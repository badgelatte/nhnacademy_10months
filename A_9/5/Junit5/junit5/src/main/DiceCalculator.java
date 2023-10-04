package main;

public class DiceCalculator {
    public static int addDice(Dice first, Dice second) {
        int result;
        result = first.getNumber() + second.getNumber();
        return result;
    }
    
    public static int subDice(Dice first, Dice second) {
        int result;
        result = Math.abs(first.getNumber() - second.getNumber());
        return result;
    }

    public static int mulDice(Dice first, Dice second) {
        int result;
        result = first.getNumber() * second.getNumber();
        return result;
    }

    public static int divDice(Dice first, Dice second) {
        int result;
        result = first.getNumber() / second.getNumber();
        return result;
    }
    
    public static boolean isOdd(Dice dice) {
        boolean result;
        if(dice.getNumber() % 2 != 0) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }

    public static boolean isEven(Dice dice) {
        boolean result;
        if(dice.getNumber() % 2 == 0) {
            result = true;
        }
        else {
            result = false;
        }
        return result;
    }
}
