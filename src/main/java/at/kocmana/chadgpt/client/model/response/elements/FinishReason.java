package at.kocmana.chadgpt.client.model.response.elements;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum FinishReason {
    STOP,
    LENGTH,
    CONTENT_FILTER,
    NULL,
    UNKNOWN;

    @JsonCreator
    public static FinishReason fromJson(String json) {
        return Arrays.stream(FinishReason.values())
                .filter(enumValue -> enumValue.toString().equalsIgnoreCase(json))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
