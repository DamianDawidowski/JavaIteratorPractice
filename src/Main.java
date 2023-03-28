import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static ListIterator<City> iterator;
    public static void main(String[] args) {
        boolean active = true;
        int index;
//        City[] destinations = {new City("Krakow", 0),
//                new City("Katowice", 75),
//
//                new City("Poznan", 450),
//                new City("Wroclaw", 250),
//                new City("Lodz", 700),
//                new City("Warsaw", 850),
//                new City("Bialystok", 1110)
//        };
        LinkedList<City> townDestinations = new LinkedList<City>();
        addDestination(townDestinations, new City("Krakow", 0));
        addDestination(townDestinations, new City("Katowice", 75));
        addDestination(townDestinations, new City("Wroclaw", 250));
        addDestination(townDestinations, new City("Bialystok", 1110));
        addDestination(townDestinations, new City("Warsaw", 850));
        addDestination(townDestinations, new City("Lodz", 700));

        iterator = townDestinations.listIterator();
//        for (City town :destinations ) {
//            if (!townDestinations.contains(town)) townDestinations.add(town);
//        }
        while (active)  {
            printMenu();
            switch (sc.nextLine().toUpperCase().substring(0, 1)) {
                case "B": case "b":
                    backward();
                    break;
                case "F": case "f":
                    forward();
                    break;
                case "L": case "l":
                    index = iterator.nextIndex();
                    listPlaces(townDestinations);
                    iterator = townDestinations.listIterator(index);
                    break;
                case "M": case "m":
                    break;
                case "Q": case "q":
                    active = false;
                    break;
                default:
            }
        }
    }
    private static void printMenu() {
        System.out.println("""
 
                           Available actions (select word or letter):
                           (F)orward
                           (B)ackward
                           (L)ist Places
                           (M)enu
                           (Q)uit""");
        System.out.print(": ");
    }
    private static void listPlaces(LinkedList<City> list) {
        iterator = list.listIterator();
        City currentTown;
        System.out.printf("\n%-20s  %-20s\n", "Town", "Distance from Krakow(in km)");
        while (iterator.hasNext()) {
            currentTown = iterator.next();
            System.out.printf("%-20s  %-20s\n", currentTown.getName(), currentTown.getDistance());
        }
    }
    private static void forward() {
        if (!iterator.hasNext()) {
            System.out.println("Now in " + iterator.previous().getName() + ", which is the last destination.");
            iterator.next();
        }
        else System.out.println("Now in " + iterator.next().getName());
    }
    private static void backward() {
        if (!iterator.hasPrevious()) {
            System.out.println("Now in " + iterator.next().getName() + ", which is the first destination.");
            iterator.previous();
        }
        else System.out.println("Now in " + iterator.previous().getName());
    }
    private static void addDestination(LinkedList<City> list, City city) {
        if (list.contains(city)) {
            System.out.println("The city " + city.getName()+" is already on the itinerary");
            return;
        }
        for (City p : list) {
            if (p.getName().equalsIgnoreCase(city.getName())) {
                System.out.println("The city " + city.getName()+" is already on the itinerary");
                return;
            }
        }
        int index = 0;
        for (var listPlace : list) {
            if (city.getDistance() < listPlace.getDistance()) {
                list.add(index, city);
                return;
            }
            index++;
        }
        list.add(city);
    }
}