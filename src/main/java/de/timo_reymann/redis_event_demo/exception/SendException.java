package de.timo_reymann.redis_event_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Timo Reymann
 * @since 17.11.18
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SendException extends RuntimeException {
    public SendException(String cause) {
        super("Error sending message: " + cause);
    }
}
