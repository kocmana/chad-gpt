package at.kocmana.chadgpt.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static java.util.Objects.requireNonNull;

@ConfigurationProperties(prefix = "chat-gpt")
public record ChatGptProperties(String baseUrl,
                                String apiKey,
                                String model,
                                Integer maxTokens
) {

    public ChatGptProperties {
        requireNonNull(baseUrl, "Chat GPT base URL is not set.");
        requireNonNull(apiKey, "Chat GPT API Key is not set");
    }

}
