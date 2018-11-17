package de.timo_reymann.redis_event_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.timo_reymann.redis_event_demo.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample controller for sending messages, this can be an controller sending messages to a websocket in
 * background and use redis to scale across multiple instances
 *
 * @author Timo Reymann
 * @since 16.11.18
 */
@RestController
public class SendController {
    private static int count;

    private final SenderService senderService;

    @Autowired
    public SendController(SenderService senderService) {
        this.senderService = senderService;
    }

    /**
     * Request Mapping for sending a message via redis, encapsulated in the {@link SenderService}
     */
    @GetMapping("/send")
    public void send() {
        senderService.send("Message: " + count++);
    }
}
