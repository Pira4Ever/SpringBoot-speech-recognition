package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ToolCallingIt {
    @Autowired
    OllamaChatModel ollamaChatModel;

    static class MathTools {
        @Tool(description = "Soma dois números inteiros, a e b")
        public int sum(int a, int b) {
            return a + b;
        }

        @Tool(description = "Subtrai dois números inteiros, a e b")
        public int diff(int a, int b) {
            return a - b;
        }
    }

    @Test
    void should_execute_sum_when_prompted() {
        var chatClient = ChatClient.builder(ollamaChatModel)
                .defaultTools(new MathTools())
                .defaultSystem("Você é um matemático").build();

        var response = chatClient.prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado final sem explicações.")
                .call().content();

        assertThat(response).isEqualTo("0");

        IO.println(response);
    }
}
