package org.lvivjavaclub.langchain4jexample;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ServiceChatRunner implements CommandLineRunner {

    private final ChatModel chatModel;

    @Autowired
    private LvivJavaClubService lvivJavaClubService;

    @Autowired
    public ServiceChatRunner(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (line.equalsIgnoreCase("clear")) {
                lvivJavaClubService.evictChatMemory(1);
                System.out.println("Starting new chat.");
                continue;
            }
            if  (line.startsWith("Topic: ")) {
                String topic = line.split("Topic: ")[1];
                System.out.println(lvivJavaClubService.announceEvent(topic));
                continue;
            }
            System.out.println(lvivJavaClubService.qna(1, line));
        }
    }
}
