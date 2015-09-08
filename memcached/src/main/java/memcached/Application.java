package memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class Application {
	public static void main(String[] args) throws Exception {
		Event event = new Event(11211, "testEvent");

		MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("121.201.63.238", 11211));
		try {
			// generate a new key.
			String k = String.valueOf(System.currentTimeMillis());

			Event e1 = (Event) memcachedClient.get(k);
			System.out.println(e1 == null);// true

			// add cache which the expiration equals three seconds.
			OperationFuture<Boolean> future = memcachedClient.add(k, 3, event);
			System.out.println(future.get());// true

			Event e2 = (Event) memcachedClient.get(k);
			System.out.println(e2 != null);// true
			System.out.println(e2.equals(event));// true

			// after three seconds,cache expired.
			TimeUnit.SECONDS.sleep(3);
			Event e3 = (Event) memcachedClient.get(k);
			System.out.println(e3 == null);// true

			future = memcachedClient.add(k, 30, event);
			System.out.println(future.get());// true

			future = memcachedClient.delete(k);
			System.out.println(future.get());// true
		} finally {
			memcachedClient.shutdown();
		}
	}
}
