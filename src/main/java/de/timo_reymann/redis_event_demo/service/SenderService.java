package de.timo_reymann.redis_event_demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.timo_reymann.redis_event_demo.bean.MessageBean;
import de.timo_reymann.redis_event_demo.configuration.RedisEventConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Timo Reymann
 * @since 16.11.18
 */
@Service
public class SenderService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SenderService(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(String message) throws JsonProcessingException {
        redisTemplate.convertAndSend(RedisEventConfiguration.TOPIC,new MessageBean(message));
    }
}
