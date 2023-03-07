package busybee.exceptions;

public class EventMissingException extends RuntimeException{
    public EventMissingException(String message) {
        super(message);
    }
}
