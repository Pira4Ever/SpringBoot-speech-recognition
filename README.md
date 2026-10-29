# SpringBoot Speech Recognition

Aplicação **Spring Boot** que usa **inteligência artificial para reconhecer fala** (speech-to-text) e integra o resultado com um modelo de linguagem e com um banco de dados MySQL. O projeto roda os modelos de voz diretamente na JVM, usando a biblioteca **jinfer**, sem depender de um serviço de transcrição externo.

> Os trechos marcados com `TODO` dependem de detalhes do seu código (endpoints, melhoria e aprendizados). Ajuste-os antes de publicar.

---

## 📌 O que o projeto faz

- Recebe áudio e o **transcreve para texto** usando o modelo **Parakeet** (reconhecimento de fala), executado localmente na JVM.
- Conta também com o modelo **Kokoro** (síntese de voz / text-to-speech), permitindo devolver respostas em áudio.
- Integra com um **LLM via Ollama** (Spring AI) para interpretar ou responder ao texto transcrito.
- **Persiste dados** em um banco **MySQL** com Spring Data JPA (o banco do `compose.yml` se chama `transaction`).
- Expõe uma **API REST** com Spring Web.

`TODO`: descreva em uma frase o caso de uso final (ex.: "o usuário fala uma despesa e a aplicação registra a transação").

---

## 🚀 Como executar a aplicação

### Pré-requisitos

| Ferramenta | Versão / observação |
|---|---|
| **JDK** | 25 (definido no `toolchain` do `build.gradle`) |
| **Docker** e **Docker Compose** | Para subir o MySQL |
| **Ollama** | Instalado e em execução (`http://localhost:11434`), com um modelo baixado |
| **Git** | Para clonar o repositório |

### Passo a passo

1. **Clone o repositório**

   ```bash
   git clone https://github.com/Pira4Ever/SpringBoot-speech-recognition.git
   cd SpringBoot-speech-recognition
   ```

2. **Baixe um modelo no Ollama** (ajuste ao modelo configurado em `application.properties`)

   ```bash
   ollama pull <nome-do-modelo>
   ```

3. **Execute a aplicação**

   ```bash
   # Linux / macOS
   ./gradlew bootRun

   # Windows
   gradlew.bat bootRun
   ```

   O projeto usa `spring-boot-docker-compose`: ao iniciar, o Spring Boot **sobe o MySQL automaticamente** a partir do `compose.yml`. Se preferir subir manualmente:

   ```bash
   docker compose up -d
   ```

   O MySQL fica exposto na porta **3307** do host (banco `transaction`, usuário `app`, senha `app`).

4. A aplicação ficará disponível em `http://localhost:8080` (porta padrão do Spring Boot).

> ℹ️ A task `bootRun` já passa as flags `--add-modules jdk.incubator.vector` e `--enable-native-access=ALL-UNNAMED`, exigidas pelas bibliotecas de inferência. Se executar o `.jar` diretamente, inclua essas mesmas flags:
>
> ```bash
> ./gradlew build
> java --add-modules jdk.incubator.vector --enable-native-access=ALL-UNNAMED -jar build/libs/*.jar
> ```

---

## ✨ Qual melhoria foi implementada

`TODO`: descreva aqui a melhoria que **você** implementou no desafio. Pontos que costumam ajudar a explicá-la:

- **Problema:** o que faltava ou funcionava mal antes?
- **Solução:** o que foi alterado/adicionado (classes, endpoints, configuração)?
- **Resultado:** qual o ganho (desempenho, usabilidade, organização, cobertura de testes)?

Exemplo de formato:

> Foi adicionada a persistência das transcrições no MySQL, permitindo consultar o histórico de áudios processados.

---

## 🛠️ Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.1.2** (Web, Data JPA, Docker Compose support)
- **Spring AI 2.0.2** com integração ao **Ollama**
- **jinfer 0.3.1** (inferência de modelos na JVM)
  - `jinfer-parakeet`: reconhecimento de fala (STT)
  - `jinfer-kokoro`: síntese de voz (TTS)
  - `jam-native` e `jam-vector`: aceleração nativa/vetorial
- **MySQL 9.7.4** (via Docker Compose)
- **Gradle** (com wrapper) e **Lombok**
- **JUnit 5** e **Spring Boot Test**

---

## 🧪 Como testar o fluxo principal

### 1. Testes automatizados

```bash
./gradlew test
```

### 2. Teste manual do fluxo de reconhecimento de fala

1. Confirme que o **Ollama** está rodando e que o **MySQL** subiu (`docker compose ps`).
2. Inicie a aplicação com `./gradlew bootRun`.
3. Envie um arquivo de áudio (por exemplo, `.wav`) para o endpoint de transcrição:

   ```bash
   curl -X POST http://localhost:8080/<endpoint-de-transcricao> \
        -F "file=@./exemplo.wav"
   ```

   `TODO`: substitua `<endpoint-de-transcricao>`, o nome do campo (`file`) e o formato aceito pelos valores reais do seu controller.

4. Verifique a resposta: o texto transcrito (e, se aplicável, a resposta gerada pelo LLM).
5. Confira se os dados foram salvos no banco:

   ```bash
   docker compose exec database mysql -uapp -papp transaction -e "SHOW TABLES;"
   ```

---

## 📚 O que aprendi durante o desafio

`TODO`: revise e personalize esta seção com a sua experiência. Um rascunho com base na stack do projeto:

- Como **executar modelos de IA localmente na JVM**, sem depender de APIs externas de transcrição.
- A necessidade de configurar a JVM para recursos nativos e vetoriais (`jdk.incubator.vector` e `--enable-native-access`).
- Como integrar o **Spring AI** com o **Ollama** para usar um LLM local dentro de uma aplicação Spring Boot.
- Como usar o **Docker Compose com Spring Boot** para provisionar o banco automaticamente em desenvolvimento.
- A importância de organizar o fluxo **áudio → texto → processamento → persistência** em camadas bem definidas.
- Boas práticas de documentação para que outra pessoa consiga executar e testar o projeto.

---

## 📄 Licença

`TODO`: informe a licença do projeto (ou remova esta seção).
