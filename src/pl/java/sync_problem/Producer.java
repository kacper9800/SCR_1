package pl.java.sync_problem;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private BlockingQueue<Item> storage;

    private Random random = new Random();

    public Producer(int storageSize) {
        this.storage = new ArrayBlockingQueue<>(storageSize);
    }

    @Override
    public void run() {
        try {
            while (true) {
                produce();
                System.out.println("Produced by: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void produce() throws InterruptedException {
        Item item = new Item(random.nextInt(10));
        storage.put(item);
        System.out.println("Produce " + item.getId());
    }

    public Item consume() throws InterruptedException {
        return storage.take();
    }
}
