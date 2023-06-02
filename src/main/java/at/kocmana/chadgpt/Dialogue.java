package at.kocmana.chadgpt;

import at.kocmana.chadgpt.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Dialogue {

    private final ChatGptAdapter adapter;
    private final List<ChatMessage> chatHistory;

    @Autowired
    public Dialogue(ChatGptAdapter adapter) {
        this.adapter = adapter;
        this.chatHistory = new ArrayList<>();
    }

    ChatMessage sendMessage(ChatMessage request){
        chatHistory.add(request);
        var response = adapter.sendMessage(request);
        chatHistory.add(response);

        return response;
    }

    List<ChatMessage> getChatHistory(){
        return List.copyOf(chatHistory);
    }

}
