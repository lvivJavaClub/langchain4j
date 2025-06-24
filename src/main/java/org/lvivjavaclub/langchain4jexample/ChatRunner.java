package org.lvivjavaclub.langchain4jexample;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class ChatRunner implements CommandLineRunner {

    private final ChatModel chatModel;

    @Autowired
    public ChatRunner(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        ChatMemory memory = TokenWindowChatMemory.withMaxTokens(100,
                new OpenAiTokenCountEstimator(OpenAiChatModelName.GPT_4_O_MINI));
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (line.equalsIgnoreCase("clear")) {
                memory.clear();
                System.out.println("Starting new chat.");
                continue;
            }
            UserMessage userMessage = UserMessage.userMessage(line);
            memory.add(userMessage);
            ChatResponse answer = chatModel.chat(memory.messages());
            memory.add(answer.aiMessage());
            System.out.println(answer.aiMessage().text());
        }
    }
}
