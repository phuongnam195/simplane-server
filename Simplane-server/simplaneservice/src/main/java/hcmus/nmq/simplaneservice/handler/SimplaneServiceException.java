package hcmus.nmq.simplaneservice.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 2:04 PM 4/17/2022
 * LeHongQuan
 */

public class SimplaneServiceException extends ResponseStatusException {
    @Getter
    private Object data;

    public SimplaneServiceException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public SimplaneServiceException(String message, Object data) {
        super(HttpStatus.NOT_FOUND, message);
        this.data = data;
    }
}