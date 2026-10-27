package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OllamaAiChatModelIt {
    @Autowired
    OllamaApi ollamaApi;

    @Test
    void should_receive_response_when_chatModelIsCalled() {
        var options = OllamaChatOptions.builder()
                .model("llama3.1")
                .temperature(0.7)
                .build();

        var chatModel = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .options(options)
                .build();

        var response = chatModel.call("Gere um registro de budgeting, com descrição de gasto, valor em reais e local");

        assertThat(response).isNotEmpty();
        IO.println(response);
    }
}
