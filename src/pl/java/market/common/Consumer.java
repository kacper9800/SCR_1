package pl.java.market.common;

import pl.java.market.Market;
import pl.java.market.MarketItem;

import java.util.Map;

/**
 * Konsumer - klient który odwiedza market z listą zakupów
 */
public class Consumer extends Thread {

    /**
     * Odwiedzany marker
     */
    private Market market;
    /**
     * Lista zakupów
     */
    private Map<MarketItem, Integer> marketItemToItemsCountMap;

    public Consumer(Market market, Map<MarketItem, Integer> marketItemToItemsCountMap) {
        this.market = market;
        this.marketItemToItemsCountMap = marketItemToItemsCountMap;
    }

    /**
     * Pętla po każdym elemencie na liście zakupów i zakup (skonsumowanie) odpowiedniej ilości
     */
    @Override
    public void run() {
        try {
            for (Map.Entry<MarketItem, Integer> entry : marketItemToItemsCountMap.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    Item consumedItem = market.consumeMarketItem(entry.getKey());
                    System.out.println("Consumer consumed market item: " + consumedItem.getName() + " consumer: " + Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
