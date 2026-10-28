package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OllamaChatClientIt {
    @Autowired
    OllamaChatModel ollamaChatModel;

    @Test
    void should_execute_sum_when_prompted() {
        var chatClient = ChatClient.builder(ollamaChatModel).defaultSystem("Você é um matemático").build();

        var response = chatClient.prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado final sem explicações.").call().content();

        assertThat(response).contains("0");

        IO.println(response);
    }
}
