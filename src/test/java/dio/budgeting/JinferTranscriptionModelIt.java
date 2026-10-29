package dio.budgeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JinferTranscriptionModelIt {

    @Autowired
    TranscriptionModel transcriptionModel;

    @ParameterizedTest
    @CsvSource({
            "recording-1.wav, 80 reais",
            "recording-2.wav, 40 reais",
            "recording-3.wav, 120 reais",
            "recording-4.wav, 90 reais",
            "recording-5.wav, 200 reais",
            "recording-6.wav, 60 reais",
    })
    void should_transcript_audio_successfully(String filename, String valor) {
        var recording = new ClassPathResource("audio/" + filename);

        var response = transcriptionModel.transcribe(recording);
        assertThat(response).isNotEmpty();
        assertThat(response).contains(valor);

        IO.println(response);
    }
}
