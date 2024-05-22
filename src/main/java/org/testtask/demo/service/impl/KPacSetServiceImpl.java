package org.testtask.demo.service.impl;

import org.springframework.stereotype.Service;
import org.testtask.demo.entity.KnowledgePacSet;
import org.testtask.demo.repository.KPacSetRepository;
import org.testtask.demo.service.KPacSetService;

import java.util.List;

@Service
public class KPacSetServiceImpl implements KPacSetService {

    private final KPacSetRepository kPacSetRepository;

    public KPacSetServiceImpl(KPacSetRepository kPacSetRepository) {
        this.kPacSetRepository = kPacSetRepository;
    }

    @Override
    public void create(KnowledgePacSet knowledgePacSet) {
        kPacSetRepository.create(knowledgePacSet);
    }

    @Override
    public KnowledgePacSet findById(Long id) {
        return kPacSetRepository.findById(id);
    }

    @Override
    public List<KnowledgePacSet> findAll() {
        return kPacSetRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        kPacSetRepository.delete(id);
    }
}
