package cinema;


import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static int ROWS;
    private static int COLUMNS;

    private static int [][] seats;
    private static Scanner scanner = new Scanner(System.in);

    public static void printNumberOfArguments(int... numbers) {
        Arrays.stream(numbers).forEach(e -> System.out.print(e + " "));
    }
    
    public static void main(String[] args) {
        initCinema();

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 0:
                    return;
                case 1:
                    printSchema();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    getStatistics();
                    break;
            }
            System.out.println();
        }
    }

    private static void getStatistics() {
        int purchasedTickets = getNumberPurchasedTickets();
        double percentage = purchasedTickets / (double) (ROWS * COLUMNS);
        int currentIncome = getCurrentIncome();
        int totalIncome = getTotalIncome();
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage * 100) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $"+ totalIncome);

    }

    private static int getTotalIncome() {
        int totalIncome = 0;
        for (int i = 0; i < ROWS; i++) {
            int priceInTheRow = getPrice(i + 1);
            totalIncome += priceInTheRow * COLUMNS;
        }
        return totalIncome;
    }

    private static int getCurrentIncome() {
        int income = 0;
        for (int[] ints : seats) {
            for (int anInt : ints) {
                income += anInt;
            }
        }
        return income;
    }

    private static int getNumberPurchasedTickets() {
        int cnt = 0;
        for (int[] ints : seats) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void initCinema() {


        System.out.println("Enter the number of rows:");
        ROWS = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        COLUMNS = scanner.nextInt();

        if (ROWS > 9 || COLUMNS > 9) {
            return;
        }

        seats = new int[ROWS][COLUMNS];

    }

    private static int getPrice(int rowN) {
        int price = 0;

        if (ROWS * COLUMNS <= 60) {
            price = 10;

        } else {
            int firstHalf = ROWS / 2;
            if (rowN <= firstHalf) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }

    private static void buyTicket() {
        while (true) {
            System.out.println("Enter a row number:");
            System.out.print("> ");
            int rowN = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            System.out.print("> ");
            int columnN = scanner.nextInt();

            if (rowN < 1 || rowN > ROWS || columnN < 1 || columnN > COLUMNS) {
                System.out.println("Wrong input!");
                continue;
            }
            if (seats[rowN - 1][columnN - 1] > 0) {
                System.out.println("That ticket has already been purchased ");
                continue;
            }

            int price = getPrice(rowN);
            System.out.printf("Ticket price: $%d \n", price);
            seats[rowN - 1][columnN - 1] = price;
            break;
        }
    }

    private static void printSchema() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= COLUMNS; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < COLUMNS; j++) {
                if (seats[i][j] == 0) {
                    System.out.print(" S");
                } else {
                    System.out.print(" B");
                }
            }
            System.out.println();
        }
    }
}