package com.ewcode.friendsbigball.bet.producer;

import com.ewcode.friendsbigball.common.entity.Costumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class BetProducer {

    private static void startTask(Costumer costumer, int duration) {
        System.out.println("Started  task1");

        try {
            Thread.sleep(Duration.ofSeconds(duration).toMillis());
        } catch (InterruptedException e) {
            System.out.println("exception");
            throw new RuntimeException(e);
        }

        System.out.println("TASK 2");
        costumer.setName("name test");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Finished task1 ");
    }

    public void test() throws InterruptedException {
        Costumer costumer = new Costumer();
        System.out.println("costumer = " + costumer);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> startTask(costumer, 5);

        Runnable task2 = () -> startTask(costumer, 7);

        List<Runnable> tasks = List.of(task1, task2);

        for (var task : tasks) {
            executorService.execute(task);
        }

        executorService.shutdown();
        boolean terminated = executorService.awaitTermination(1, TimeUnit.MINUTES);
        if (!terminated) {
            System.out.println("Executor service não terminou todas as tasks");
        }
        System.out.println("costumer = " + costumer);
    }
}