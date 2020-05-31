package pl.java.market;

import pl.java.market.common.Consumer;
import pl.java.market.common.Item;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Market uzupełnia swoje zapasy od producentów oraz umożliwia sprzedaż klienotwi {@link Consumer}
 */
public class Market {

    /**
     * Rozmiar magazynu w markecie
     */
    public static final int BANANA_STORAGE_SIZE = 6;
    public static final int GRAPE_STORAGE_SIZE = 5;
    public static final int PINEAPPLE_STORAGE_SIZE = 4;
    public static final int WATERMELON_STORAGE_SIZE = 3;


    /**
     * Każdy "item" ma swoją przestrzeń w magazynie
     */
    private static Map<MarketItem, BlockingQueue<Item>> itemToStorageMap = new EnumMap<>(MarketItem.class);
    private static Map<MarketItem, Integer> itemToStorageSizeMap = new EnumMap<>(MarketItem.class);
    private static Map<MarketItem, Boolean> itemToInitFillUpMap = new EnumMap<>(MarketItem.class);

    /**
     * Stop thread when market is closed
     */
    private volatile boolean marketCloseFlag = false;

    public static List<MarketItem> marketItems = Arrays.asList(MarketItem.BANANA, MarketItem.GRAPE, MarketItem.PINEAPPLE, MarketItem.WATERMELON);

    static {
        // Bananas
        itemToStorageSizeMap.put(MarketItem.BANANA, BANANA_STORAGE_SIZE);
        itemToStorageMap.put(MarketItem.BANANA, new ArrayBlockingQueue<>(BANANA_STORAGE_SIZE));
        itemToInitFillUpMap.put(MarketItem.BANANA, false);
        // Grapes
        itemToStorageSizeMap.put(MarketItem.GRAPE, GRAPE_STORAGE_SIZE);
        itemToStorageMap.put(MarketItem.GRAPE, new ArrayBlockingQueue<>(GRAPE_STORAGE_SIZE));
        itemToInitFillUpMap.put(MarketItem.GRAPE, false);
        // Pineapples
        itemToStorageSizeMap.put(MarketItem.PINEAPPLE, PINEAPPLE_STORAGE_SIZE);
        itemToStorageMap.put(MarketItem.PINEAPPLE, new ArrayBlockingQueue<>(PINEAPPLE_STORAGE_SIZE));
        itemToInitFillUpMap.put(MarketItem.PINEAPPLE, false);
        // Watermelons
        itemToStorageSizeMap.put(MarketItem.WATERMELON, WATERMELON_STORAGE_SIZE);
        itemToStorageMap.put(MarketItem.WATERMELON, new ArrayBlockingQueue<>(WATERMELON_STORAGE_SIZE));
        itemToInitFillUpMap.put(MarketItem.WATERMELON, false);
    }

    private ProducerManager producerManager;

    public Market(ProducerManager producerManager) {
        this.producerManager = producerManager;
    }

    /**
     * Po otwarciu marketu następuje uzupełnienie magazynu każdego "item'u" do maksymalnej pojemości {ITEM_STORAGE_SIZE}
     * Market kontaktuje się z producentem dopiero gdy fill up zostanie zakończony
     */
    public void openMarket() {
        System.out.println("Market is open");
        for (Map.Entry<MarketItem, BlockingQueue<Item>> entry : itemToStorageMap.entrySet()) {
            int storageDiff = itemToStorageSizeMap.get(entry.getKey()) - itemToStorageMap.get(entry.getKey()).size();
            fillUpMarketStorage(entry.getKey(), storageDiff);
        }
    }

    /**
     * Market kontaktuje sie z producentem i konsumuje wymagane "item'y"
     * Jeśli magazyn danego "item'u" jest pełny następuje czekanie na klienta
     * Jeśli magazyn producenta jest pusty następuje czekanie na wyprodukowanie "item'ów"
     *
     * @param marketItem - "item" do skonsumowania
     * @param fillUpSize - ilość "item'ów" do skonsumowania od producenta
     */
    private void fillUpMarketStorage(MarketItem marketItem, int fillUpSize) {
        final Runnable fillUpMarketStorage = () -> {
            BlockingQueue<Item> storage = itemToStorageMap.get(marketItem);
            for (int i = 0; i < fillUpSize; i++) {
                try {
                    if (marketCloseFlag) {
                        throw new InterruptedException("Stop - market is closed");
                    }
                    Item item = producerManager.getProducer(marketItem).consumeItem();
                    storage.put(item);
                    System.out.println("Add item: " + item.getName() + " to market storage: " + storage.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            itemToInitFillUpMap.replace(marketItem, true);
            System.out.println("Item: " + marketItem + " successful filled up");
        };
        new Thread(fillUpMarketStorage).start();
    }

    /**
     * Zwraca wybrany "item" z marketu,
     * Jeśli magazyn jest mniejszy niz 1/3 maksymalnej pojemności następuje kontakt z produentem w celu uzupełnienia
     * Uzupełnienie to zawsze 1/2 całkowitej pojemności
     *
     * @return - skonsumowany przez klienta "item"
     */
    public Item consumeMarketItem(MarketItem marketItem) throws InterruptedException {
        fillUpStorageIfLow(marketItem);
        BlockingQueue<Item> storage = itemToStorageMap.get(marketItem);
        Item item = storage.take();
        System.out.println("Consumer consumed market item: " + item.getName() + " storage size: " + storage.size());
        return item;
    }

    public void fillUpStorageIfLow(MarketItem marketItem) {
        if (!itemToInitFillUpMap.get(marketItem)) {
            System.out.println("No need to fill up item: " + marketItem + ". Init fill up still processing");
            return;
        }
        BlockingQueue<Item> storage = itemToStorageMap.get(marketItem);
        int maxStorage = itemToStorageSizeMap.get(marketItem);
        // Fill if lower than 1/3 max
        if (storage.size() < maxStorage / 3) {
            System.out.println("Storage of " + marketItem + " is low. Start filling up");
            itemToInitFillUpMap.replace(marketItem, false);
            // Add half max storage
            fillUpMarketStorage(marketItem, maxStorage / 2);
        }
    }

    public void closeMarket() {
        marketCloseFlag = true;
    }
}
