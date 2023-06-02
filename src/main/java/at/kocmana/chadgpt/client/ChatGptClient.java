package at.kocmana.chadgpt.client;

import at.kocmana.chadgpt.client.model.request.ChatCompletionRequest;
import at.kocmana.chadgpt.client.model.response.ChatCompletionResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@HttpExchange(accept = APPLICATION_JSON_VALUE, contentType = APPLICATION_JSON_VALUE)
public interface ChatGptClient {

    @PostExchange
    ChatCompletionResponse submitMessage(@RequestBody ChatCompletionRequest query);

}
