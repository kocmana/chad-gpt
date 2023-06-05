package at.kocmana.chadgpt.model;

import java.util.Arrays;

public enum Persona {
    CHAD("You are a stereotypical chad.",
            "Answer as if you were a stereotypical chad."),
    THREE_YEAR_OLD("You are helpful assistant providing support to a three year old kid.",
            "Answer as if you would explain it to a three year old kid."),
    GRANNY("You are a helpful old grandmother.",
            "Explain the following as if you would be an old grandmother.") ,
    POE("You are a helpful assistant talking like the author Edgar Allen Poe.",
            "Answer the following query as if you would be Edgar Allen Poe.");

    private final String systemContent;
    private final String messageHint;


    Persona(String systemContent, String messageHint) {
        this.systemContent = systemContent;
        this.messageHint = messageHint;
    }

    public static Persona fromString(String personaAsString) {
        return Arrays.stream(Persona.values())
                .filter(persona -> persona.name().equalsIgnoreCase(personaAsString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No persona found for name %s. Available options: %s"
                        .formatted(personaAsString, getAvailableOptions())));
    }

    private static String getAvailableOptions() {
        return String.join(", ", Arrays.toString(Persona.values()));
    }

    public String getSystemContent() {
        return systemContent;
    }

    public String getMessageHint() {
        return messageHint;
    }
}
