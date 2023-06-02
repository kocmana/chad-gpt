package at.kocmana.chadgpt;

import at.kocmana.chadgpt.model.ChatMessage;
import at.kocmana.chadgpt.model.ChatResponse;
import at.kocmana.chadgpt.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class ChatService {

    private final Dialogue dialogue;

    @Autowired
    public ChatService(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public ChatResponse handleQuery(ChatMessage query) {
        query = nonNull(query.persona())
                ? query
                : new ChatMessage(Persona.CHAD, query.message());

        var response = dialogue.sendMessage(query);
        return new ChatResponse(response.persona(), response.message());
    }
}
