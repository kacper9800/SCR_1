package pl.java.market;

import pl.java.market.common.Consumer;

import java.util.EnumMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {

        // Producenci są niezależni od marketu i zaczynają produkcję oraz zapełnianie włąsnych magazynów
        ProducerManager producerManager = new ProducerManager();

        Market market = new Market(producerManager);
        // Market zostaje otwarty - magazyn zostaje uzupelniany
        market.openMarket();

        // Zakup 2x Apple i 2x Carrot
        Map<MarketItem, Integer> consumer1GroceryMap = new EnumMap<>(MarketItem.class);
        consumer1GroceryMap.put(MarketItem.APPLE, 2);
        consumer1GroceryMap.put(MarketItem.CARROT, 2);

        // Zakup 4x Apple i 1x Carrot i 5x Banana
        Map<MarketItem, Integer> consumer2GroceryMap = new EnumMap<>(MarketItem.class);
        consumer2GroceryMap.put(MarketItem.APPLE, 4);
        consumer2GroceryMap.put(MarketItem.CARROT, 1);
        consumer2GroceryMap.put(MarketItem.BANANA, 5);

        // Consumer 1 odwiedza sklep i konsumuje elementu z listy zakupów
        Consumer consumer1 = new Consumer(market, consumer1GroceryMap);
        consumer1.setName("Consumer 1");
        consumer1.start();

        // Consumer 2 odwiedza sklep i konsumuje elementu z listy zakupów
        Consumer consumer2 = new Consumer(market, consumer2GroceryMap);
        consumer2.setName("Consumer 2");
        consumer2.start();
    }
}
