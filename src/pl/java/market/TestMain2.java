//package pl.java.market;
//
//import pl.java.market.common.Consumer;
//
//import java.util.EnumMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class TestMain2 {
//
//    private static ProducerManager producerManager;
//    private static Market market;
//
//    public static void main(String[] args) {
//
//        System.out.println("Witaj");
//        Scanner scanner = new Scanner(System.in);
//        Integer choice;
//        do {
//            printMarketMenu();
//            choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    handleProducersProduction();
//                    break;
//                case 2:
//                    handleMarketOpen();
//                    break;
//                case 3:
//                    handleMadeGroceries();
//                    break;
//                case 4:
//                    System.exit(0);
//                    break;
//            }
//        } while (true);
//    }
//
//    private static void printMarketMenu() {
//        System.out.println("Wybierz opcje:");
//        if (producerManager == null) {
//            System.out.println("1) Rozpocznij produkcje");
//        } else {
//            System.out.println("1) Zatrzymaj produkcje");
//        }
//        if (market == null) {
//            System.out.println("2) Otwórz market");
//        } else {
//            System.out.println("2) Zamknij market");
//        }
//        System.out.println("3) Zrob zakupy w markecie");
//        System.out.println("4) Wyjdz");
//    }
//
//    private static void handleProducersProduction() {
//        if (producerManager == null) {
//            startProducersProduction();
//        } else {
//            stopProducersProduction();
//        }
//    }
//
//    private static void startProducersProduction() {
//        producerManager = new ProducerManager();
//    }
//
//    private static void stopProducersProduction() {
//        producerManager.stopAllProduction();
//        producerManager = null;
//    }
//
//    private static void handleMarketOpen() {
//        if (producerManager == null) {
//            System.out.println("Najpierw rozpocznij produkcje\n");
//        } else if (market == null) {
//            openMarket();
//        } else {
//            closeMarket();
//        }
//    }
//
//    private static void openMarket() {
//        market = new Market(producerManager);
//        market.openMarket();
//    }
//
//    private static void closeMarket() {
//        market.closeMarket();
//        market = null;
//    }
//
//    private static void handleMadeGroceries() {
//        if (market == null) {
//            System.out.println("Market jest aktualnie zamkniety");
//            return;
//        }
//        System.out.println("Podaj zakupy:");
//        Map<MarketItem, Integer> groceriesMap = new EnumMap<>(MarketItem.class);
//        Scanner scanner = new Scanner(System.in);
//        for (MarketItem item : Market.marketItems) {
//            System.out.println("Podaj ilość: " + item);
//            int quantity = scanner.nextInt();
//            groceriesMap.put(item, quantity);
//        }
//        Consumer consumer = new Consumer(market, groceriesMap);
//        consumer.start();
//    }
//}
