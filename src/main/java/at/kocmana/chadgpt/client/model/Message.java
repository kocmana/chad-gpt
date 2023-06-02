package at.kocmana.chadgpt.client.model;

public record Message(
        Role role,
        String content
) {
}
