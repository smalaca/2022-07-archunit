package com.smalaca.gtd.projectmanagement.infrastructure.repository.jpa.idea;

import com.smalaca.gtd.projectmanagement.domain.idea.Idea;

import java.util.UUID;

public class IdeaTestRepository {
    private final SpringDataJpaIdeaRepository repository;

    public IdeaTestRepository(SpringDataJpaIdeaRepository repository) {
        this.repository = repository;
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public UUID save(Idea idea) {
        return repository.save(idea).id();
    }
}