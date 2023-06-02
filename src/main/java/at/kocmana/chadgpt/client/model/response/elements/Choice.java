package at.kocmana.chadgpt.client.model.response.elements;

import at.kocmana.chadgpt.client.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(
        Message message,
        @JsonProperty("finish_reason")
        FinishReason finishReason,
        Integer index
) {
}
