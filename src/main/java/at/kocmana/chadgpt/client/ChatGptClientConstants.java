package at.kocmana.chadgpt.client;

import java.util.function.Function;

public final class ChatGptClientConstants {

    public static final String API_HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String API_HEADER_AUTHORIZATION_VALUE_PREFIX = "Bearer ";
    public static final Function<String, String> API_HEADER_AUTHORIZATION_VALUE_PREFIX_APPENDER = API_HEADER_AUTHORIZATION_VALUE_PREFIX::concat;
    private ChatGptClientConstants() {
    }

}
