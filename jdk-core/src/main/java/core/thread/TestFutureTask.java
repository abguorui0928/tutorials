package core.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask {
	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = Executors.newFixedThreadPool(1);
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "just a test.";
			}
		});
		if (!future.isDone()) {
			future.cancel(true);
		}
		try {
			System.out.println(future.get());
		} catch (CancellationException e) {
			System.out.println("task is canceled.");
		}
		threadPool.shutdownNow();
	}
}
