package rental.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class OverbookedException extends RuntimeException {
    public OverbookedException(String exception) {
        super(exception);
    }
}
