package pl.java.sync_problem;

public class Consumer extends Thread {

    private Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Item item = producer.consume();
                System.out.println("Consume " + item.getId());
                System.out.println("Consumed by: " + Thread.currentThread().getName());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
