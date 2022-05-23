import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static String[][] cinema; // Declaration of a Cinema Array
    static int seatsFront = 10;
    static int seatsBack = 8;

    static double numberOfSeats = 0;
    static int seatsTaken = 0;
    static double percentageOfSeats = 0;
    static int currentIncome = 0;

    public static int[] buyersChoice = new int[2];

    public static void main(String[] args) {
        loadCinema();
        mainMenu();
    }

    public static void loadCinema() {

        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();
        cinema = new String[rows+1][seats+1];

        numberOfSeats = rows*seats;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = (" ");
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = ("S");
                }
            }
        }
    }

    public static int totalPossibleIncome(String[][] cinema) {
        if (((cinema.length-1) * (cinema[0].length-1)) <= 60) {
            return ((cinema.length-1) * (cinema[0].length-1))*seatsFront;
        } else {
            int ticketsIncomeFront = (((cinema.length-1) / 2)*(cinema[0].length-1))*seatsFront;
            int ticketsIncomeBack = (((cinema.length-1) - ((cinema.length-1) / 2))*(cinema[0].length-1))*seatsBack;
            return ticketsIncomeBack + ticketsIncomeFront;
        }
    }

    public static void mainMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int userInput = input.nextInt();

        switch (userInput) {
            case 1:
                printCinema(cinema);
                break;
            case 2:
                pickSeat(cinema, buyersChoice);
                break;
            case 3:
                statistics(cinema);
                break;
            case 0:
                return;
        } mainMenu();

    }

    public static void printCinema(String[][] cinema) {
        System.out.println("\nCinema:");
        for (String[] strings : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }

    public static void pickSeat(String[][] cinema, int[] buyersChoice) {
        System.out.println("\nEnter a row number:");
        buyersChoice[0] = input.nextInt();
        System.out.println("Enter a seat number:");
        buyersChoice[1] = input.nextInt();

        if (buyersChoice[0] > (cinema.length-1) || buyersChoice[1] > cinema[0].length-1) {
            System.out.println("Wrong input!");
            pickSeat(cinema, buyersChoice);
        } else if (cinema[buyersChoice[0]][buyersChoice[1]].equals("B"))  {
            System.out.println("That ticket has already been purchased!");
            pickSeat(cinema, buyersChoice);
        } else {
            cinema[buyersChoice[0]][buyersChoice[1]] = "B";
            seatsTaken++;
            calculatePrice(cinema, buyersChoice);
        }
    }

    public static void statistics(String[][] cinema) {

        if ((numberOfSeats / seatsTaken) == 0) {
            percentageOfSeats = 0;
        } else {
            percentageOfSeats = (seatsTaken / numberOfSeats) * 100;
        }

        System.out.printf("\nNumber of purchased tickets: %d", seatsTaken);
        System.out.printf("\nPercentage: %.2f%%", percentageOfSeats);
        System.out.printf("\nCurrent income: $%d", currentIncome);
        System.out.printf("\nTotal possible income: $%d\n", totalPossibleIncome(cinema));
    }

    public static void calculatePrice(String[][] cinema, int[] buyersChoice) {
        if (((cinema.length-1) * (cinema[0].length-1)) <= 60) {
            currentIncome += seatsFront;
            System.out.printf("Ticket Price: $%d\n", seatsFront);
        } else {
            if (buyersChoice[0] <= ((cinema.length-1) / 2)) {
                currentIncome += seatsFront;
                System.out.printf("Ticket Price: $%d\n", seatsFront);
            } else {
                currentIncome += seatsBack;
                System.out.printf("Ticket Price: $%d\n", seatsBack);
            }
        }
    }
}
