package at.kocmana.chadgpt.client.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
    SYSTEM,
    USER,
    ASSISTANT,
    UNKNOWN;

    @JsonValue
    public static Role fromJson(String json) {
        return Arrays.stream(Role.values())
                .filter(enumValue -> enumValue.toString().equalsIgnoreCase(json))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown role \"%s\"".formatted(json)));
    }

    @JsonValue
    public String toJson() {
        return this.name().toLowerCase();
    }
}
