import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }

    public static String calc(String input){

        return null;
    }

    public static int romanToArabic(String input) {
        String rimNum = input.toUpperCase();
        int result = 0;

        List rimNums = RimWorld.getReverseNumList();

        int i = 0;

        while ((rimNum.length() > 0) && (i < rimNums.size())) {
            RimWorld symbol = (RimWorld) rimNums.get(i);
            if (rimNum.startsWith(symbol.name())) {
                result += symbol.getValue();
                rimNum = rimNum.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (rimNum.length() > 0) {
            throw new IllegalArgumentException(input + " невозможно конвертировать");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number >= 4000)) {
            throw new IllegalArgumentException(number + " это число не входит в диапазон (0,4000)");
        }

        List rimNums = RimWorld.getReverseNumList();

        int i = 0;
        StringBuilder rimNum = new StringBuilder();

        while ((number > 0) && (i < rimNums.size())) {
            RimWorld symbol = (RimWorld) rimNums.get(i);
            if (symbol.getValue() <= number) {
                rimNum.append(symbol.name());
                number -= symbol.getValue();
            } else {
                i++;
            }
        }

        return rimNum.toString();
    }

}