import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Laba_5 {
    private static final AtomicInteger TreesReady = new AtomicInteger(0);
    private static final BlockingQueue<Integer> hole = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Integer> plant = new LinkedBlockingQueue<>();
    private static final CyclicBarrier barrier = new CyclicBarrier(3);
    private static final AtomicInteger TreesNumber = new AtomicInteger(0);
    private static final AtomicInteger Holes = new AtomicInteger(0);
    private static final AtomicInteger Planted = new AtomicInteger(0);


    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int trees = tryReadInt("number of trees?\n", scanner);
        scanner.close();
        TreesNumber.set(trees);
        new Thread(dig).start();
        new Thread(planter).start();
        new Thread(bind).start();
    }

    private static int tryReadInt(final String value, final Scanner scanner) {
        do {
            System.out.print(value);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("it's not a number");
                scanner.next();
            }
        } while (true);
    }

    private static final Runnable dig = () -> {
        while (Holes.get() != TreesNumber.get()) {
            try {
                final int id = Holes.incrementAndGet();
                System.err.println("dig " +id);
                hole.add(id);
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static final Runnable planter = () -> {
        while (Planted.get() != TreesNumber.get()) {
            try {
                final Integer id = hole.take();
                System.err.println("plant " +id);
                Planted.set(id);
                plant.add(id);
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static final Runnable bind = () -> {
        while (TreesReady.get() != TreesNumber.get()) {
            try {
                final Integer id = plant.take();
                System.err.println("bind " +id);
                TreesReady.getAndIncrement();
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


}
