package at.kocmana.chadgpt;

import at.kocmana.chadgpt.client.ChatGptClient;
import at.kocmana.chadgpt.client.ChatGptProperties;
import at.kocmana.chadgpt.client.model.ChatGptException;
import at.kocmana.chadgpt.client.model.Message;
import at.kocmana.chadgpt.client.model.Role;
import at.kocmana.chadgpt.client.model.request.ChatCompletionRequest;
import at.kocmana.chadgpt.client.model.response.ChatCompletionResponse;
import at.kocmana.chadgpt.model.ChatMessage;
import at.kocmana.chadgpt.model.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatGptAdapter {

    private final ChatGptClient client;
    private final ChatGptProperties properties;

    @Autowired
    public ChatGptAdapter(ChatGptClient client, ChatGptProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    public ChatMessage sendMessage(ChatMessage chatMessage) {
        var request = generateRequestFor(chatMessage);
        var response = client.submitMessage(request);

        log.debug("Response: {}", response);

        return new ChatMessage(chatMessage.persona(), extractMessageFrom(response));
    }

    private ChatCompletionRequest generateRequestFor(ChatMessage chatMessage) {
        var message = prepareMessage(chatMessage);
        var assistantHint = prepareAssistant(chatMessage.persona());

        return new ChatCompletionRequest(
                properties.model(),
                List.of(assistantHint, message),
                properties.maxTokens()
        );
    }

    private Message prepareAssistant(Persona persona) {
        return new Message(Role.ASSISTANT, persona.getSystemContent());
    }

    private Message prepareMessage(ChatMessage chatMessage) {
        var message = chatMessage.persona().getMessageHint().concat(chatMessage.message());

        return new Message(
                Role.USER,
                message
        );
    }

    private String extractMessageFrom(ChatCompletionResponse response) {
        var responseChoice = response.choices().stream()
                .filter(choice -> choice.message().role().equals(Role.ASSISTANT))
                .findFirst()
                .orElseThrow(()-> new ChatGptException("Could not find response."));

        return responseChoice.message().content();
    }
}
