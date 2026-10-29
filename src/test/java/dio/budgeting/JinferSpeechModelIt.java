package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JinferSpeechModelIt {

    @Autowired
    TextToSpeechModel textToSpeechModel;

    @Test
    void should_produce_audio_when_text_is_provided() throws IOException {
        var response = textToSpeechModel.call("O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento?");
        assertThat(response).isNotNull();
        assertThat(response).hasSizeGreaterThan(1024);

        var tempFile = Files.createTempFile("AUDIO_", ".mp3");
        Files.write(tempFile, response);
        IO.println(tempFile.toAbsolutePath());
    }
}
