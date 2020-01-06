import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String example = reader.readLine();    //считываем с консоли пример
        String[] parcExample = example.split(" "); //  парсим на число, математическую операци и второе число
        if (parcExample.length != 3) throw new NumberFormatException(); // если распарсилось не на 3 элемента значит некоректные данные, бросаю эксепшен
        String first = parcExample[0];
        String second = parcExample[2];
        String oper = parcExample[1];
        if (checkRom(first) && checkRom(second)) { //проверяю на римский цифры, если римские работаю с римским калькулятором
            RomCalc romCalc = new RomCalc(first, oper, second);
            romCalc.calRom();
        } else if (checkAr(first) && checkAr(second)) { // если арабские работаю с арабским
            ArabCalc arabCalc = new ArabCalc(first, oper, second);
            arabCalc.calAr();
        } else throw new NumberFormatException(); // иначе выбрасываю эксепшен т.к. некоректные входные данные
    }

    private static boolean checkAr(String x) {   // проверка на арабскую цифру
        boolean flag = false;
        switch (x) {
            case "1" :
            case "3" :
            case "2" :
            case "4" :
            case "5" :
            case "6" :
            case "7" :
            case "8" :
            case "9" :
            case "10" :
                flag = true;
            break;
        }
        return flag;
    }

    private static boolean checkRom(String x) { //проверка на римскую цифру
        boolean flag = false;
        switch (x) {
            case "I" :
            case "II" :
            case "III" :
            case "IV" :
            case "V" :
            case "VI" :
            case "VII" :
            case "VIII" :
            case "IX" :
            case "X" :
                flag = true;
                break;
        }
        return flag;
    }
}
class RomCalc  {
    private String first;
    private String second;
    private String operation;
    public RomCalc(String first, String operation, String second) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }
    public void calRom() {  // метод вычеслений
        int x = number(this.first);
        int y = number(this.second);
        int i = 0;
        switch (operation) {
            case "+" :
                i = x + y;
                System.out.println(NumberConvertManager.transform_number_to_roman_numeral(i)); // для вывода римских цифр исползуется класс перевода арамских в римские, взял уже готовое решение этого класса.
                break;
            case "-" :
                i = x - y;
                System.out.println(NumberConvertManager.transform_number_to_roman_numeral(i));
                break;
            case "*" :
                i = x * y;
                System.out.println(NumberConvertManager.transform_number_to_roman_numeral(i));
                break;
            case "/" :
                double z = x/(double)y;
                System.out.println(NumberConvertManager.transform_number_to_roman_numeral((int)z)); // округляю дл интаб т.к. работаем с целыми вроде в задание говорится что на выходи числа могут быть любыми, но есть и условие что работтаем с целыми
        }                                                                                           //тут либо проверять на целочислиность и бросать эксепшен что получается не целое число, либо округлять. Это так я поня по заданию.
    }
    int number(String s) {// для простоты вычеслений меняется римская цифра на арабскую
        int x = 0;
        switch (s) {
            case "I" :
                x = 1;
                break;
            case  "II" :
                x = 2;
                break;
            case  "III" :
                x = 3;
                break;
            case  "IV" :
                x = 4;
                break;
            case  "V" :
                x = 5;
                break;
            case  "VI" :
                x = 6;
                break;
            case  "VII" :
                x = 7;
                break;
            case  "VIII" :
                x = 8;
                break;
            case  "IX" :
                x = 9;
                break;
            case  "X" :
                x = 10;
                break;
        }
        return x;
    }
}
class ArabCalc {
    private String first;
    private String second;
    private String operation;

    public ArabCalc(String first, String operation, String second) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }
    public void calAr() {
        int x = Integer.parseInt(first);
        int y = Integer.parseInt(second);
        int i;
        switch (operation) {
            case "+" :
                i = x + y;
                System.out.println(i);
                break;
            case "-" :
                i = x - y;
                System.out.println(i);
                break;
            case "*" :
                i = x * y;
                System.out.println(i);
                break;
            case "/" :
                double z = x/(double)y;
                System.out.println((int)z); // округляем для работы с целыми(по заданию)
        }
    }
}

class NumberConvertManager {  // готовое решение подоем арабское число и потихоньку дробим на римское
    public static String transform_number_to_roman_numeral(int number) {
        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i ++) {
            while (number >= roman_value_list[i]) {
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }
}