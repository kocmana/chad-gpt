package at.kocmana.chadgpt;

import at.kocmana.chadgpt.model.ChatMessage;
import at.kocmana.chadgpt.model.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.context.InteractionMode;
import org.springframework.stereotype.Component;

@Component
@Command
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Command(command = "say", alias = "tell me", interactionMode = InteractionMode.NONINTERACTIVE)
    public String say(
            @Option(longNames = {"as", "persona"},
                    description = "Set the persona to use for this query.",
                    arity = CommandRegistration.OptionArity.ZERO_OR_ONE, defaultValue = "CHAD")
            String persona,
            @Option(description = "The query text.",
                    required = true, arity = CommandRegistration.OptionArity.ONE_OR_MORE)
            String query) {

        log.info("QUERY:\t{}\t{}", persona, query);

        var sanitizedQuery = sanitizeQuery(query);
        var message = new ChatMessage(Persona.fromString(persona), sanitizedQuery);
        var response = chatService.handleQuery(message);
        return "%s: %s".formatted(
                response.persona(),
                response.response()
        );
    }

    private String sanitizeQuery(String query) {
        return query.replace(',', ' ');
    }

}
