package org.testtask.demo.repository;

import org.testtask.demo.entity.KnowledgePacSet;

import java.util.List;

public interface KPacSetRepository {

    void create(KnowledgePacSet knowledgePacSet);

    KnowledgePacSet findById(Long id);

    List<KnowledgePacSet> findAll();

    void delete(Long id);

}
