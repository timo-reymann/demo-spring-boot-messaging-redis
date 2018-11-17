package de.timo_reymann.redis_event_demo.service;

import de.timo_reymann.redis_event_demo.bean.MessageBean;
import de.timo_reymann.redis_event_demo.configuration.RedisEventConfiguration;
import de.timo_reymann.redis_event_demo.exception.SendException;
import io.lettuce.core.RedisCommandTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Timo Reymann
 * @since 16.11.18
 */
@Service
public class SenderService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final CountDownLatch latch;
    private static Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    public SenderService(RedisTemplate<String, Object> redisTemplate, CountDownLatch latch) {
        this.redisTemplate = redisTemplate;
        this.latch = latch;
    }

    /**
     * Send message to redis
     *
     * @param message Message
     */
    public void send(String message) {
        try {
            redisTemplate.convertAndSend(RedisEventConfiguration.TOPIC, new MessageBean(message));
        } catch (QueryTimeoutException e) {
            log.error("RedisCommand timed out", e);
            throw new SendException("Queue not reachable");
        } catch (Exception e) {
            log.error("Unknown error while sending", e);
            throw new SendException("Something bad happened");
        }

        try {
            latch.await(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Error sending message", e);
        }
    }
}
