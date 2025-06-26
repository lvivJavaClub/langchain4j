package org.lvivjavaclub.langchain4jexample;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

public class AiDeveloper {

    interface SeniorDeveloper {

        @SystemMessage("You are a senior developer answering question after a public talk. Answer politely and precise.")
        String qna(String question);

        @SystemMessage("You are a senior developer answering casual question in the office. Answer correctly but with a bit of " +
                "irony and sarcasm")
        String casualQuestion(String question);
    }

    public static void main(String[] args) {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");

        ChatModel model = OpenAiChatModel.builder()
                .apiKey(openaiApiKey)
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();

        SeniorDeveloper developer = AiServices.create(SeniorDeveloper.class, model);
        System.out.println(developer.qna("Why should I use Spring framework?"));
        System.out.println(developer.casualQuestion("Why should I use Spring framework?"));
    }
}
