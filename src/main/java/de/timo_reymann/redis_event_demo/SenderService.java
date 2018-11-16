package de.timo_reymann.redis_event_demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Timo Reymann
 * @since 16.11.18
 */
@Service
public class SenderService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SenderService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(String message) throws JsonProcessingException {
        redisTemplate.convertAndSend(RedisEventConfiguration.TOPIC, objectMapper.writeValueAsString(new Message(message)));
    }

    public static class Message {
        private String content;

        public Message(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
