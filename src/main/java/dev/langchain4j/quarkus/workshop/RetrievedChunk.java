package dev.langchain4j.quarkus.workshop;

import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * One row per chunk retrieved by the RAG content retriever for a given user question.
 */
@Entity
public class RetrievedChunk extends PanacheEntity {

    @Column(nullable = false, columnDefinition = "text")
    public String question;

    @Column(nullable = false, columnDefinition = "text")
    public String chunkText;

    public Double score;

    public String sourceDocument;

    @Column(nullable = false)
    public Instant retrievedAt;
}
