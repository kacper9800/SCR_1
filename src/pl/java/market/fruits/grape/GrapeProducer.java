package pl.java.market.fruits.grape;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 winogrono co 3 sekundy, w magazynie może pomieścić łącznie 15
 */
public class GrapeProducer extends Producer<Grape> {

    private static final int MAX_STORAGE = 15;
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
    protected Grape createItem() {
        return new Grape();
    }
}
