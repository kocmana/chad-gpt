package at.kocmana.chadgpt.model;

public enum Persona {
    CHAD("You are a stereotypical chad.",
            "Answer as if you were a stereotypical chad."),
    THREE_YEAR_OLD("You are helpful assistant providing support to a three year old kid.",
            "Answer as if you would explain it to a three year old kid."),
    GRANNY("You are a helpful old grandmother.",
            "Explain the following as if you would be an old grandmother.") ,
    POE("You are a helpful assistant talking like the author Edgar Allen Poe.",
            "Answer the following query as if you would be Edgar Allen Poe."),
    GLADOS("You are GLADOS, the AI from Valve's Portal Game.",
            "Answer the following question as if you were GLADOS.");

    private final String systemContent;
    private final String messageHint;


    Persona(String systemContent, String messageHint) {
        this.systemContent = systemContent;
        this.messageHint = messageHint;
    }

    public String getSystemContent() {
        return systemContent;
    }

    public String getMessageHint() {
        return messageHint;
    }
}
