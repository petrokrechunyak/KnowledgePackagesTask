package org.testtask.demo.service;

import org.testtask.demo.entity.KnowledgePacSet;

import java.util.List;

public interface KPacSetService {

    void create(KnowledgePacSet knowledgePacSet);

    KnowledgePacSet findById(Long id);

    List<KnowledgePacSet> findAll();

    void delete(Long id);

}
