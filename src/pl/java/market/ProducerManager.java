package pl.java.market;

import pl.java.market.common.Item;
import pl.java.market.common.Producer;
import pl.java.market.fruits.banana.BananaProducer;
import pl.java.market.fruits.grape.GrapeProducer;
import pl.java.market.fruits.pineapple.PineappleProducer;
import pl.java.market.fruits.watermelon.WatermelonProducer;

import java.util.EnumMap;
import java.util.Map;

/**
 * Zarządza wszystkimi producentami
 * Po inicjalizacji rozpoczyna produkcję i zapełnianie magazy każdego producenta
 */
public class ProducerManager {

    private Map<MarketItem, Producer<? extends Item>> itemToProducerMap = new EnumMap<>(MarketItem.class);

    public ProducerManager() {
        itemToProducerMap.put(MarketItem.BANANA, new BananaProducer());
        itemToProducerMap.put(MarketItem.GRAPE, new GrapeProducer());
        itemToProducerMap.put(MarketItem.PINEAPPLE, new PineappleProducer());
        itemToProducerMap.put(MarketItem.WATERMELON, new WatermelonProducer());
        for (Map.Entry<MarketItem, Producer<? extends Item>> entry : itemToProducerMap.entrySet()) {
            entry.getValue().start();
        }
    }

    public Producer<? extends Item> getProducer(MarketItem marketItem) {
        return itemToProducerMap.get(marketItem);
    }

    public void stopAllProduction() {
        for (Map.Entry<MarketItem, Producer<? extends Item>> entry : itemToProducerMap.entrySet()) {
            entry.getValue().interrupt();
        }
    }

}
