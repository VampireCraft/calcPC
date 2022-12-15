import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(calc(new Scanner(System.in).nextLine()));
    }

    public static String calc(String input){
        String fullTask = input.toUpperCase();
        boolean operator = false;
        boolean firstRim = false;
        boolean secondRim = false;
        String delimeter = "";
        StringBuilder newTask = new StringBuilder();

        for (char symbol:fullTask.toCharArray()) {
            switch (symbol){
                case 'I':
                case 'V':
                case 'X':
                case 'L':
                case 'C':
                case 'D':
                case 'M':
                    newTask.append(symbol);
                    if (operator)
                        firstRim = true;
                    else
                        secondRim = true;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    newTask.append(symbol);
                    break;
                case ' ':
                case ' ':
                    break;
                case '+':
                    newTask.append('_');
                    delimeter = "_";
                    if (operator)
                        throw new IllegalArgumentException(input + " формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    else
                        operator = true;
                    break;
                case '-':
                case '/':
                case '*':
                    newTask.append(symbol);
                    delimeter = String.valueOf(symbol);
                    if (operator)
                        throw new IllegalArgumentException(input + " формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    else
                        operator = true;
                    break;
                default:
                    throw new IllegalArgumentException(input + " неправильно поставлена задача ");
            }
        }

        if (firstRim==secondRim){
            String[] nums = newTask.toString().split(delimeter,2);
            if (firstRim){
                switch (delimeter){
                    case "_":
                        return arabicToRoman(romanToArabic(nums[0])+romanToArabic(nums[1]));
                    case "-":
                        return arabicToRoman(romanToArabic(nums[0])-romanToArabic(nums[1]));
                    case "/":
                        return arabicToRoman(romanToArabic(nums[0])/romanToArabic(nums[1]));
                    case "*":
                        return arabicToRoman(romanToArabic(nums[0])*romanToArabic(nums[1]));
                    default:
                        throw new IllegalArgumentException(input + " строка не является математической операцией  ");
                }
            } else {
                switch (delimeter){
                    case "_":
                        return String.valueOf(Integer.parseInt(nums[0])+Integer.parseInt(nums[1]));
                    case "-":
                        return String.valueOf(Integer.parseInt(nums[0])-Integer.parseInt(nums[1]));
                    case "/":
                        return String.valueOf(Integer.parseInt(nums[0])/Integer.parseInt(nums[1]));
                    case "*":
                        return String.valueOf(Integer.parseInt(nums[0])*Integer.parseInt(nums[1]));
                    default:
                        throw new IllegalArgumentException(input + " строка не является математической операцией  ");
                }
            }
        }
        else
            throw new IllegalArgumentException(input + " используются одновременно разные системы счисления");
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