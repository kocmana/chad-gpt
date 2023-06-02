package at.kocmana.chadgpt.client.model.request;

import at.kocmana.chadgpt.client.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatCompletionRequest(String model,
                                    List<Message> messages,
                                    @JsonProperty("max_tokens") Integer maxTokens
) {
}
