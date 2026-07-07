package dev.langchain4j.quarkus.workshop;

import java.time.Instant;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.ContentMetadata;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverErrorContext;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverListener;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverResponseContext;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * Persists every chunk returned by the content retriever, so retrieval history
 * (question, chunk, similarity score, source document, timestamp) can be audited later.
 */
@ApplicationScoped
public class RetrievalLoggingListener implements ContentRetrieverListener {

    @Override
    @Transactional
    public void onResponse(ContentRetrieverResponseContext context) {
        String question = context.query().text();
        Instant now = Instant.now();

        for (Content content : context.contents()) {
            RetrievedChunk chunk = new RetrievedChunk();
            chunk.question = question;
            chunk.chunkText = content.textSegment().text();
            chunk.score = (Double) content.metadata().get(ContentMetadata.SCORE);
            chunk.sourceDocument = content.textSegment().metadata().getString(Document.FILE_NAME);
            chunk.retrievedAt = now;
            chunk.persist();
        }

        Log.infof("Persisted %d retrieved chunk(s) for question: %s", context.contents().size(), question);
    }

    @Override
    public void onError(ContentRetrieverErrorContext context) {
        Log.warnf(context.error(), "Content retrieval failed for query: %s", context.query().text());
    }
}
