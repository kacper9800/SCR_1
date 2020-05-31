package pl.java.market.fruits.banana;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 banana co 5 sekund, w magazynie może pomieścić łącznie 30
 */
public class BananaProducer extends Producer<Banana> {

    private static final int MAX_STORAGE = 30;
    // Produce one element every 5 seconds
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
    protected Banana createItem() {
        return new Banana();
    }
}
