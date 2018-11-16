package de.timo_reymann.redis_event_demo;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author Timo Reymann
 * @since 16.11.18
 */
@Service
public class NotificationReceiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message);
    }
}
