package at.kocmana.chadgpt;

import at.kocmana.chadgpt.model.ChatMessage;
import at.kocmana.chadgpt.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chad")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping()
    public ResponseEntity<ChatResponse> handleChatRequest(@RequestBody ChatMessage chatMessage) {

        var response = chatService.handleQuery(chatMessage);
        return ResponseEntity.ok(response);
    }

}
