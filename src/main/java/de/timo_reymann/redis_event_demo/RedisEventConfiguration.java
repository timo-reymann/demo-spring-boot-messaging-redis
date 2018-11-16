package de.timo_reymann.redis_event_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.concurrent.CountDownLatch;

/**
 * @author Timo Reymann
 * @since 16.11.18
 */
@Configuration
public class RedisEventConfiguration {
    public static final String TOPIC = "chat";

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new NotificationReceiver(), new PatternTopic(TOPIC));
        return container;
    }

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    @Primary
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
