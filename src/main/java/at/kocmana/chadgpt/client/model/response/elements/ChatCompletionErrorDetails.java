package at.kocmana.chadgpt.client.model.response.elements;

public record ChatCompletionErrorDetails(

    String message,
    String type,
    String param,
    String code
){}
