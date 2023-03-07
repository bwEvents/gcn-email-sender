package busybee.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handle(ServiceException ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        log.debug("Error making request ->" + request.getRequestURI());
        log.debug("With query Param ->" + request.getQueryString());
        log.error("Error is", ex.getMessage());
        return new ResponseEntity(ex.getResponseBody(), HttpStatus.OK);
    }

    @ExceptionHandler({EventMissingException.class})
    public ResponseEntity<Object> handleEventMissing(EventMissingException ex,
                                                     HttpServletRequest request, HttpServletResponse response) {
        log.debug("Error making request ->" + request.getRequestURI());
        log.debug("With query Param ->" + request.getQueryString());
        log.error("Error is", ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }

}
