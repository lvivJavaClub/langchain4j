package org.lvivjavaclub.langchain4jexample;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

public class HelloWorld {

    public static void main(String[] args) {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(openaiApiKey)
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();

        String answer = model.chat("Describe why is Lviv JavaClub the best!");
        System.out.println(answer);

        System.out.println("ANNOUNCEMENT:");
        Prompt prompt = StructuredPromptProcessor.toPrompt(new LvivJavaClubAnnouncement("langchain4j", 2));
        String announcement = model.chat(prompt.text());
        System.out.println(announcement);
    }

    @StructuredPrompt({"I need an announcement that will encourage people to attend the next event of Lviv JavaClub. The topic " +
            "is “{{topic}}. It should be short, {{sentences}} sentences, describe why this topic is important and encourage " +
            "people to join the event."})
    static class LvivJavaClubAnnouncement {
        public LvivJavaClubAnnouncement(String topic, int sentences) {
            this.topic = topic;
            this.sentences = sentences;
        }

        String topic;
        int sentences;
    }
}
