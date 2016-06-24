package kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class ProducerTest {

	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.1.200:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
		try {
			for (int i = 0; i < 100; i++) {
				kafkaProducer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
				TimeUnit.SECONDS.sleep(3);
			}
		} finally {
			kafkaProducer.close();
		}
	}

}
