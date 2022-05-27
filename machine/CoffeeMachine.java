package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int coffeeBeans = 120;
    static int money = 550;
    static int disposableCups = 9;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean operating = true;
        while (operating) {
            System.out.println("Write action (buy, fill, take):");
            String option = scanner.nextLine();
            option = option.toLowerCase();
            switch (option) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    cashOut();
                    break;
                case "remaining":
                    printState();
                    System.out.println();
                    break;
                case "exit":
                    operating = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();

    }

    public static void printState() {
        System.out.println("The coffee machine has: \n" +
                water + " ml of water\n" +
                milk + " ml of milk\n" +
                coffeeBeans + " g of coffee beans\n" +
                disposableCups + " disposable cups\n" +
                "$" + money + " of money");
    }

    public static void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                verifyAndProcessTransaction(250, 0, 16, 4);
                break;
            case "2":
                verifyAndProcessTransaction(350, 75, 20, 7);
                break;
            case "3":
                verifyAndProcessTransaction(200, 100, 12, 6);
                break;
            case "back":
                break;
            default:
                System.out.println("Invalid option selected.");
                break;
        }
    }

    public static void verifyAndProcessTransaction(int waterTemp, int milkTemp, int coffeeBeansTemp, int moneyTemp) {
        if ((water - waterTemp >= 0) && (coffeeBeans - coffeeBeansTemp >= 0) && (milk - milkTemp >= 0) && disposableCups > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterTemp;
            coffeeBeans -= coffeeBeansTemp;
            money += moneyTemp;
            milk -= milkTemp;
            disposableCups -= 1;
            System.out.println();
        } else {
            if (water - waterTemp < 0) {
                System.out.println("Sorry, not enough water!");
            } else if (coffeeBeans - coffeeBeansTemp < 0) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (milk - milkTemp < 0) {
                System.out.println("Sorry, not enough milk!");
            } else if (disposableCups <= 0) {
                System.out.println("Sorry, not enough disposable cups!");
            }
        }
    }

    public static void fillMachine() {
        System.out.println("How many ml of water you want to add: ");
        int waterAmount = scanner.nextInt();
        System.out.println("How many ml of milk you want to add: ");
        int milkAmount = scanner.nextInt();
        System.out.println("How many grams of coffee beans you want to add: ");
        int coffeeBeansAmount = scanner.nextInt();
        System.out.println("How many disposable cups of coffee you want to add: ");
        int disposableCupsAmount = scanner.nextInt();
        scanner.nextLine();
        if (waterAmount >= 0 && milkAmount >= 0 && coffeeBeansAmount >= 0 && disposableCupsAmount >= 0) {
            water += waterAmount;
            milk += milkAmount;
            coffeeBeans += coffeeBeansAmount;
            disposableCups += disposableCupsAmount;
            System.out.println();
        } else {
            System.out.println("Error when adding resources. Invalid quantity.");
        }
    }

    public static void cashOut() {
        System.out.println("I gave you $" + money);
        money = 0;
        System.out.println();
    }
}
