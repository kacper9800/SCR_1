package pl.java.market.banana;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 banana co 3 sekundy, w magazynie może pomieścić łącznie 15
 */
public class BananaProducer extends Producer<Banana> {

    private static final int MAX_STORAGE = 15;
    // Produce one element every 2 seconds
    private static final long PRODUCE_TIME = 3000;

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
