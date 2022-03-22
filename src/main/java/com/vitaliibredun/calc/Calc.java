package com.vitaliibredun.calc;

import java.util.Scanner;

public class Calc {

    public static void main(String[] args) {

        int num1;
        int num2;
        String operation;

        boolean l1 = true;
        boolean l2 = true;

        try(Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String num1str = words[0];
            operation = words[1];
            String num2str = words[2];

            if (words.length>3){
                throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию");
            }
    
            String[] arab = new String[] {"10", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] rome = new String[] {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    
            for (int i = 0; i < arab.length; i++) {
                if (rome[i].equals(num1str)) {
                    num1str = arab[i];
                    l1 = false;
                }
                if (rome[i].equals(num2str)) {
                    num2str = arab[i];
                    l2 = false;
                }
            }

            num1 = Integer.parseInt(num1str.trim());
            num2 = Integer.parseInt(num2str.trim());

            if (l1 != l2) {
                throw new IllegalArgumentException("Только арабские или только римские числа");
            }
            if (num1 >= 11 || num2 >= 11) {
                throw new IllegalArgumentException("Введите числа не больше 10");
            } else if (num1 <= 0 || num2 <= 0) {
                throw new IllegalArgumentException("Введите числа больше нуля");
            } else if (l1) {
                System.out.println(calc(num1, num2, operation));
            } else {
                int result = calc(num1, num2, operation);
                if (result<=0){
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                String romanNumeralOutput = toRoman(result);
                System.out.println(romanNumeralOutput);
            }

        } catch (NumberFormatException e) {
            System.err.println("Веедите числа не больше X");
        }
    }

    public static int calc(int num1, int num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Не верный формат ввода");
        }
    }

    public static String toRoman(int number) {
        int[] romanValueList = new int[] {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanCharList = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < romanValueList.length; i += 1) {
            while (number >= romanValueList[i]) {
                number -= romanValueList[i];
                res.append(romanCharList[i]);
            }
        }
        return res.toString();
    }
}
