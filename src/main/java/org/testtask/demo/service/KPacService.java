package org.testtask.demo.service;

import org.testtask.demo.entity.KnowledgePac;

import java.util.List;

public interface KPacService {

    void create(KnowledgePac kPac);

    KnowledgePac findById(Long id);

    List<KnowledgePac> findAll();

    void delete(Long id);
}
