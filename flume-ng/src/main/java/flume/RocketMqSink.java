package flume;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventHelper;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by guor on 2016/3/17.
 */
public class RocketmqSink extends AbstractSink implements Configurable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketmqSink.class);
    private DefaultMQProducer defaultMQProducer;
    private Properties parameters;

    @Override
    public void configure(Context context) {
        ImmutableMap<String, String> props = context.getParameters();
        parameters = new Properties();
        for (String key : props.keySet()) {
            String value = props.get(key);
            this.parameters.put(key, value);
        }
    }

    @Override
    public synchronized void start() {
        super.start();
        String producerGroup = Preconditions.checkNotNull((String) this.parameters.get("producerGroup"), "producerGroup is required");
        String namesrvAddr = Preconditions.checkNotNull((String) this.parameters.get("namesrvAddr"), "namesrvAddr is required");
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setDefaultTopicQueueNums(1);
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            LOGGER.error("start rocketmq producer failed.", e);
        }
    }

    @Override
    public Status process() throws EventDeliveryException {
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        txn.begin();
        try {
            Event event = ch.take();
            String topic = Preconditions.checkNotNull((String) this.parameters.get("topic.name"), "topic.name is required");
            defaultMQProducer.send(new Message(topic, event.getBody()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Send Message to RocketMQ : [" + new String(event.getBody(), "UTF-8") + "] -- [" + EventHelper.dumpEvent(event) + "]");
            }
            txn.commit();
            return Status.READY;
        } catch (Throwable t) {
            txn.rollback();
            if (t instanceof Error) {
                throw (Error) t;
            }
            return Status.BACKOFF;
        } finally {
            txn.close();
        }
    }
}
