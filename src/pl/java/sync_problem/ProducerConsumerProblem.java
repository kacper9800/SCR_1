package pl.java.sync_problem;

public class ProducerConsumerProblem {
    public static void main(String[] args) {

        Producer producer = new Producer(5);
        producer.setName("Producer 1");

        Consumer consumer1 = new Consumer(producer);
        consumer1.setName("Consumer 1");
        Consumer consumer2 = new Consumer(producer);
        consumer2.setName("Consumer 2");
        Consumer consumer3 = new Consumer(producer);
        consumer3.setName("Consumer 3");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
