package org.testtask.demo.repository;

import org.testtask.demo.entity.KnowledgePac;

import java.util.List;

public interface KPacRepository {

    void create(KnowledgePac kPac);

    KnowledgePac findById(Long id);

    List<KnowledgePac> findAll();

    void delete(Long id);
}
