package pl.java.market.carrot;

import pl.java.market.common.Producer;

/**
 * Produkuje 1 marchewkę co 2 sekundy, w magazynie może pomieścić łącznie 12
 */
public class CarrotProducer extends Producer<Carrot> {

    private static final int MAX_STORAGE = 12;
    // Produce one element every 2 seconds
    private static final long PRODUCE_TIME = 2000;

    @Override
    protected int getMaxStorageSize() {
        return MAX_STORAGE;
    }

    @Override
    protected long getProduceTime() {
        return PRODUCE_TIME;
    }

    @Override
    protected Carrot createItem() {
        return new Carrot();
    }
}
