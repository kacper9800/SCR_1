package pl.java.market.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Bazowa klaas producenta
 *
 * @param <T> - produkoawny "item"
 */
public abstract class Producer<T extends Item> extends Thread {

    /**
     * Magazyn na wypordukowany "item"
     */
    private BlockingQueue<T> storage;

    private final long produceTime;

    public Producer() {
        this.produceTime = getProduceTime();
        this.storage = new ArrayBlockingQueue<>(getMaxStorageSize());
    }

    protected abstract int getMaxStorageSize();

    protected abstract long getProduceTime();

    /**
     * Produkowanie elementu co określony czas {@see produceTime}, aż do zapełnienia magazynu {@see storage},
     * gdzię następuje oczekiwanie na konsumenta, w celu zwonienia miejsca w magazynie
     */
    @Override
    public void run() {
        try {
            while (true) {
                produceItem();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wyprodukowany elelemnt zostaje umieszczony w magazynie {@see storage} (jeśli posiada wolne miejsce)
     */
    private void produceItem() throws InterruptedException {
        T item = createItem();
        storage.put(item);
        System.out.println(this.getClass().getSimpleName() + " produced item: " + item.getName() + " storage size: " + storage.size());
        Thread.sleep(produceTime);
    }

    protected abstract T createItem();

    /**
     * @return zwraca produkowany element pobrany z magazynu, jeśli magazyn jest pusty, następuje oczekiwanie na producenta
     */
    public T consumeItem() throws InterruptedException {
        T item = storage.take();
        System.out.println("Consumer consumed " + this.getClass().getSimpleName() + " item: " + item.getName() + " storage size: " + storage.size());
        return item;
    }
}
