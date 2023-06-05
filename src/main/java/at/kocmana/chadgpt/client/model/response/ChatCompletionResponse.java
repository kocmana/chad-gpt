package at.kocmana.chadgpt.client.model.response;

import at.kocmana.chadgpt.client.model.response.elements.ChatCompletionErrorDetails;
import at.kocmana.chadgpt.client.model.response.elements.Choice;
import at.kocmana.chadgpt.client.model.response.elements.Usage;

import java.time.Instant;
import java.util.List;

public record ChatCompletionResponse(
        String id,
        String object,
        Instant created,
        String model,
        Usage usage,
        List<Choice> choices,
        ChatCompletionErrorDetails error
) {

}
