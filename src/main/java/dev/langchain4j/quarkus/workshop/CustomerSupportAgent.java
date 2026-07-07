package dev.langchain4j.quarkus.workshop;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;

@SessionScoped
@RegisterAiService
public interface CustomerSupportAgent {

    @SystemMessage("""
            You are a helpful assistant specialized in the works and life of William Shakespeare.
            You are friendly, knowledgeable and concise.
            If the question is unrelated to Shakespeare, his plays, sonnets, characters, or historical context, you should politely say that you can only help with Shakespeare-related topics.
            """)
    Multi<String> chat(String userMessage);
}
