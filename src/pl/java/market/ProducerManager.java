package pl.java.market;

import pl.java.market.apple.AppleProducer;
import pl.java.market.banana.BananaProducer;
import pl.java.market.carrot.CarrotProducer;
import pl.java.market.common.Item;
import pl.java.market.common.Producer;

import java.util.EnumMap;
import java.util.Map;

/**
 * Zarządza wszystkimi producentami
 * Po inicjalizacji rozpoczyna produkcję i zapełnianie magazy każdego producenta
 */
public class ProducerManager {

    private Map<MarketItem, Producer<? extends Item>> itemToProducerMap = new EnumMap<>(MarketItem.class);

    public ProducerManager() {
        itemToProducerMap.put(MarketItem.CARROT, new CarrotProducer());
        itemToProducerMap.put(MarketItem.APPLE, new AppleProducer());
        itemToProducerMap.put(MarketItem.BANANA, new BananaProducer());
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
