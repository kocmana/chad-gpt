package at.kocmana.chadgpt.client.model;

public class ChatGptException extends RuntimeException {

    public ChatGptException(String message) {
        super(message);
    }

    public ChatGptException(String message, Throwable cause) {
        super(message, cause);
    }
}
