package pl.java.market.apple;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 jabłko co 5 sekund, w magazynie może pomieścić łącznie 30
 */
public class AppleProducer extends Producer<Apple> {

    private static final int MAX_STORAGE = 30;
    // Produce one element every 5 seconds
    private static final long PRODUCE_TIME = 5000;

    @Override
    protected int getMaxStorageSize() {
        return MAX_STORAGE;
    }

    @Override
    protected long getProduceTime() {
        return PRODUCE_TIME;
    }

    @Override
    protected Apple createItem() {
        return new Apple();
    }
}
