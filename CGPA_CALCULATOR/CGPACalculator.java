import java.util.Scanner;

public class CGPACalculator {

    public CGPACalculator() {
    }

    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        boolean var2 = true;

        while (var2) {
            System.out.println("\nCGPA Calculator Menu:");
            System.out.println("1. Calculate CGPA");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int var3 = var1.nextInt();

            switch (var3) {
                case 1:
                    calculateCGPA(var1);
                    break;
                case 2:
                    var2 = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void calculateCGPA(Scanner var0) {
        System.out.print("Enter the number of subjects: ");
        boolean var1 = false;
        int var13;

        while (true) {
            try {
                var13 = Integer.parseInt(var0.next());
                if (var13 > 0) {
                    break;
                }
                System.out.print("Number of subjects must be greater than 0. Try again: ");
            } catch (NumberFormatException var12) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }

        double[] var2 = new double[var13];
        double[] var3 = new double[var13];
        double var4 = 0.0;

        System.out.println("Enter the grades and credits for each subject:");
        for (int var6 = 0; var6 < var13; ++var6) {
            System.out.print("Grade for subject " + (var6 + 1) + ": ");
            label55:
            while (true) {
                String grade = var0.next().toUpperCase();

                switch (grade) {
                    case "A":
                        var2[var6] = 9.0;
                        break label55;
                    case "B":
                        var2[var6] = 8.0;
                        break label55;
                    case "C":
                        var2[var6] = 7.0;
                        break label55;
                    case "D":
                        var2[var6] = 6.0;
                        break label55;
                    case "F":
                        var2[var6] = 0.0;
                        break label55;
                    default:
                        System.out.print("Invalid grade. Please enter A, B, C, D, or F: ");
                }
            }

            System.out.print("Credits for subject " + (var6 + 1) + ": ");
            label65:
            while (true) {
                try {
                    var3[var6] = Double.parseDouble(var0.next());
                    if (var3[var6] > 0.0) {
                        break label65;
                    }
                    System.out.print("Credits must be greater than 0. Try again: ");
                } catch (NumberFormatException var11) {
                    System.out.print("Invalid input. Please enter a valid number of credits: ");
                }
            }

            var4 += var3[var6];
        }

        double var14 = 0.0;
        for (int var8 = 0; var8 < var13; ++var8) {
            var14 += var2[var8] * var3[var8];
        }

        double var15 = var14 / var4;
        System.out.printf("Your weighted CGPA is: %.2f\n", var15);
    }
}


