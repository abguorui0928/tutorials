package flume;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guor on 2016/3/17.
 */
public class RocketMqSource extends AbstractSource implements EventDrivenSource, Configurable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMqSource.class);
    private DefaultMQPushConsumer defaultMQPushConsumer;
    private Properties parameters;
    private ExecutorService executorService;

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
        String consumerGroup = Preconditions.checkNotNull((String) this.parameters.get("consumerGroup"), "consumerGroup is required");
        String namesrvAddr = Preconditions.checkNotNull((String) this.parameters.get("namesrvAddr"), "namesrvAddr is required");
        String topic = Preconditions.checkNotNull((String) this.parameters.get("topic.name"), "topic.name is required");

        this.executorService = Executors.newFixedThreadPool(20);
        try {
            this.defaultMQPushConsumer = new DefaultMQPushConsumer(consumerGroup);
            this.defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            this.defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
            this.defaultMQPushConsumer.subscribe(topic, null);
            this.defaultMQPushConsumer.setMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    if (!msgs.isEmpty()) {
                        Iterator<MessageExt> msgsIterator = msgs.iterator();
                        while (msgsIterator.hasNext()) {
                            MessageExt msg = msgsIterator.next();
                            executorService.submit(new ConsumerWorker(msg.getBody()));
                        }
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            this.defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            LOGGER.error("start rocketmq consumer failed.", e);
        }
    }

    class ConsumerWorker implements Runnable {
        private byte[] message;

        ConsumerWorker(byte[] message) {
            this.message = message;
        }

        @Override
        public void run() {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Receive Message [" + new String(message, Charset.forName("UTF-8")) + "]");
            }
            Event event = EventBuilder.withBody(message);
            RocketMqSource.this.getChannelProcessor().processEvent(event);
        }
    }
}
