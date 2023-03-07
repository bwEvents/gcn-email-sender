package busybee.exceptions;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private Object responseBody;
    public ServiceException(Object obj, String message) {
        super(message);
        responseBody = obj;
    }
}