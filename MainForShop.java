class Shop {
    private int productCount = 0;

    public synchronized void produceProduct() throws InterruptedException {
        while (productCount >= 3) {
            wait();
        }

        productCount++;
        System.out.println("Производитель произвел товар. Всего товаров: " + productCount);

        notify();
    }

    public synchronized void buyProduct() throws InterruptedException {
        while (productCount == 0) {
            wait();
        }

        productCount--;
        System.out.println("Покупатель купил товар. Всего товаров: " + productCount);



        notify();
    }
}

class Producer implements Runnable {
    private final Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                shop.produceProduct();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                shop.buyProduct();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MainForShop {
    public static void main(String[] args) {
        Shop shop = new Shop();

        Thread producerThread = new Thread(new Producer(shop));
        Thread consumerThread = new Thread(new Consumer(shop));

        producerThread.start();
        consumerThread.start();
    }
}
