package pl.java.market.fruits.watermelon;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 ananasa co 2 sekundy, w magazynie może pomieścić łącznie 12
 */
public class WatermelonProducer extends Producer<Watermelon> {

    private static final int MAX_STORAGE = 12;
    // Produce one element every 2 seconds
    private static final long PRODUCE_TIME = 10000;

    @Override
    protected int getMaxStorageSize() {
        return MAX_STORAGE;
    }

    @Override
    protected long getProduceTime() {
        return PRODUCE_TIME;
    }

    @Override
    protected Watermelon createItem() {
        return new Watermelon();
    }
}
