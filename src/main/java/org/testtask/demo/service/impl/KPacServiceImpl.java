package org.testtask.demo.service.impl;

import org.springframework.stereotype.Service;
import org.testtask.demo.entity.KnowledgePac;
import org.testtask.demo.repository.KPacRepository;
import org.testtask.demo.service.KPacService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class KPacServiceImpl implements KPacService {

    private final KPacRepository mainRepository;

    public KPacServiceImpl(KPacRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void create(KnowledgePac kPac) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instant = new Date().toInstant();

        LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        kPac.setCreationDate(ldt.format(formatter));
        mainRepository.create(kPac);
    }

    @Override
    public KnowledgePac findById(Long id) {
        return mainRepository.findById(id);
    }

    @Override
    public List<KnowledgePac> findAll() {
        return mainRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        mainRepository.delete(id);
    }
}
