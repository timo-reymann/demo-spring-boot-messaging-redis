package de.timo_reymann.redis_event_demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.timo_reymann.redis_event_demo.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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

    @GetMapping("/send")
    public void send() throws JsonProcessingException {
        senderService.send("Message: " + count++);
    }
}
