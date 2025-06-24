package org.lvivjavaclub.langchain4jexample;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.memory.ChatMemoryAccess;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface LvivJavaClubService extends ChatMemoryAccess {
    @SystemMessage("You know everything about Java development. Answer politely and precise.")
    String qna(@MemoryId int memoryId, @UserMessage String question);

    @UserMessage("I need an announcement that will encourage people to attend the next event of Lviv JavaClub. The topic is “{{it}}”." +
            "It should be short, 2 sentences, describe why this topic is important and encourage people to join the event.")
    String announceEvent(String topic);
}
