package at.kocmana.chadgpt.client;

import at.kocmana.chadgpt.client.model.ChatGptException;
import at.kocmana.chadgpt.client.model.response.ChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

import static at.kocmana.chadgpt.client.ChatGptClientConstants.API_HEADER_AUTHORIZATION_KEY;
import static at.kocmana.chadgpt.client.ChatGptClientConstants.API_HEADER_AUTHORIZATION_VALUE_PREFIX_APPENDER;

@Configuration
@Slf4j
public class ClientConfiguration {

    @Bean
    HttpClient httpClient() {
        return HttpClient.create()
                .responseTimeout(Duration.ofSeconds(30));
    }

    @Bean
    HttpServiceProxyFactory httpServiceProxyFactory(ChatGptProperties properties, HttpClient client) {
        var webclient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(properties.baseUrl())
                .defaultHeader(API_HEADER_AUTHORIZATION_KEY, API_HEADER_AUTHORIZATION_VALUE_PREFIX_APPENDER.apply(properties.apiKey()))
                .defaultStatusHandler(HttpStatusCode::isError,
                        foo -> foo.bodyToMono(ChatCompletionResponse.class)
                                .flatMap(body -> Mono.error(new ChatGptException(body.error().message()))))
                .build();

        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webclient))
                .blockTimeout(Duration.ofSeconds(30))
                .build();
    }


    @Bean
    public ChatGptClient chatGptClient(HttpServiceProxyFactory httpServiceProxyFactory) {
        return httpServiceProxyFactory.createClient(ChatGptClient.class);
    }

}
