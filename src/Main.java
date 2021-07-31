import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        TheResult result = new TheResult();
        result.calculateTheResult();
        if (result.ifRoman == 1) {
            result.toRomanIfNecessary(result.result);
        }
    }
}

class InputOperation {
    public static String operation;
    private static String name;
    private int numArabic1;
    private int numArabic2;
    private int solutionNumber1;
    private int solutionNumber2;
    public int confirmIfRoman = 0;
    private String numRoman1 = "";
    private String numRoman2 = "";

    public String checkInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        name = reader.readLine().replace(" ", "");

        int index1 = name.indexOf("+");
        int index1End = name.lastIndexOf("+");
        int index2 = name.indexOf("-");
        int index2End = name.lastIndexOf("-");
        int index3 = name.indexOf("*");
        int index3End = name.lastIndexOf("*");
        int index4 = name.indexOf("/");
        int index4End = name.lastIndexOf("/");

        if (index1 == index1End && index1 != -1 && index2 == -1 && index3 == -1 && index4 == -1) {
            operation = "\\+";
        } else if (index2 == index2End && index2 != -1 && index1 == -1 && index3 == -1 && index4 == -1) {
            operation = "-";
        } else if (index3 == index3End && index3 != -1 && index1 == -1 && index2 == -1 && index4 == -1) {
            operation = "\\*";
        } else if (index4 == index4End && index4 != -1 && index1 == -1 && index2 == -1 && index3 == -1) {
            operation = "/";
        } else {
            System.err.println("Ошибка ввода, введите выражение" +
                    " вида a + b, a - b, a * b, a / b");
            System.exit(0);
        }
        //System.out.println(operation);
        return operation;
    }

    public void defineNumbers() {
        int RomanToArabic1 = 0;
        int RomanToArabic2 = 0;
        String[] chars = InputOperation.name.split(operation);
        //System.out.println(Arrays.toString(chars));
        RomanFigures[] allAllowedFigures = RomanFigures.values();
        try {
            numArabic1 = Integer.parseInt(chars[0]);
            numArabic2 = Integer.parseInt(chars[1]);
            if (numArabic1 < 1 || numArabic1 > 10 || numArabic2 < 1 || numArabic2 > 10) {
                throw new UnsupportedOperationException("Ошибка ввода, используйте целые числа от 1 до 10");
            }
        } catch (UnsupportedOperationException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException ex) {
            for (Object s : allAllowedFigures) {
                //System.out.println(s.toString());
                numRoman1 = chars[0];
                RomanToArabic1++;
                if (numRoman1.equals(s.toString())) {
                    //System.out.println(RomanToArabic1);
                    for (Object c : allAllowedFigures) {
                        numRoman2 = chars[1];
                        RomanToArabic2++;
                        if (numRoman2.equals(c.toString())) {
                            //System.out.println(RomanToArabic2);
                            break;
                        }
                        numRoman2 = "";
                    }
                    break;
                }
                numRoman1 = "";
            }
            if (numRoman1.equals("") || numRoman2.equals("")) {
                System.err.println("Ошибка ввода, используйте целые числа только от 1 до 10 или только от I до X");
                System.exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка ввода, введите выражение вида a + b, a - b, a * b, a / b." +
                    "Используйте целые числа только от 1 до 10 или только от I до X.");
            System.exit(0);
        }
        if (numArabic1 != 0 && numArabic2 != 0) {
            solutionNumber1 = numArabic1;
            solutionNumber2 = numArabic2;
        }
        if (!numRoman1.equals("")) {
            solutionNumber1 = RomanToArabic1;
            solutionNumber2 = RomanToArabic2;
            confirmIfRoman = 1;
        }

    }

    public int getSolutionNumber1() {
        return this.solutionNumber1;
    }

    public int getSolutionNumber2() {
        return this.solutionNumber2;
    }
}

class TheResult {
    String operation;
    int solutionNumber1;
    int solutionNumber2;
    int result;
    int ifRoman;
    String romanResult = "";

    public void calculateTheResult() throws IOException {
        InputOperation operationUsed = new InputOperation();
        operation = operationUsed.checkInput();
        operationUsed.defineNumbers();
        ifRoman = operationUsed.confirmIfRoman;
        solutionNumber1 = operationUsed.getSolutionNumber1();
        solutionNumber2 = operationUsed.getSolutionNumber2();

        switch (operation) {
            case "\\+":
                result = solutionNumber1 + solutionNumber2;
                break;
            case "-":
                result = solutionNumber1 - solutionNumber2;
                break;
            case "\\*":
                result = solutionNumber1 * solutionNumber2;
                break;
            case "/":
                result = solutionNumber1 / solutionNumber2;
                break;
        }

        if (ifRoman != 1) {
            System.out.println(result);
        }
    }

    public void toRomanIfNecessary(int result) {
        String[] RomanNum = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        int integerPart = result / 10;
        int reminderPart = result % 10;
        String RomanNumPos;

        if (reminderPart == 0) {
            RomanNumPos = "";
        } else if (reminderPart < 0) {
            RomanNumPos = "";
            System.err.println("Ошибка расчета. В римской системе исчесления нет отрицательных чисел.");
            System.exit(0);
        } else {
            RomanNumPos = RomanNum[reminderPart - 1];
        }

        if (result == 0) {
            romanResult += "Null";
        }
        if (integerPart >= 1 && integerPart < 4) {

            romanResult = romanResult + "X".repeat(integerPart);
        }
        if (integerPart == 4) {
            romanResult = romanResult + "XL";
        }
        if (integerPart >= 5 && integerPart < 9) {
            romanResult = romanResult + "L" + "X".repeat(integerPart - 5);
        }
        if (integerPart == 9) {
            romanResult = romanResult + "XC";
        }
        if (integerPart == 10) {
            romanResult = romanResult + "C";
        }
        romanResult = romanResult + RomanNumPos;
        System.out.println(romanResult);
    }

}




