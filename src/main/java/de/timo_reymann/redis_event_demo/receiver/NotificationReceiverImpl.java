package de.timo_reymann.redis_event_demo.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Implementation for an example notification receiver
 *
 * @author Timo Reymann
 * @since 16.11.18
 */
public class NotificationReceiverImpl implements MessageListener {
    private static Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("Parsed message content_ {} >> {}", getChannel(message), getPayload(message));
        log.info("Raw message: {}", deserialize(message));
    }

    private String getChannel(Message message) {
        return new String(message.getChannel());
    }

    private Object getPayload(Message message) {
        return redisSerializer.deserialize(message.getBody());
    }

    private Object deserialize(Message message) {
        return redisTemplate.getStringSerializer().deserialize(message.getBody());
    }
}
