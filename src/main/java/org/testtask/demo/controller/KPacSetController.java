package org.testtask.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.testtask.demo.dto.KnowledgePacSetDto;
import org.testtask.demo.entity.KnowledgePac;
import org.testtask.demo.entity.KnowledgePacSet;
import org.testtask.demo.service.KPacService;
import org.testtask.demo.service.KPacSetService;

import java.util.List;

@Controller
@RequestMapping("/sets")
public class KPacSetController {

    private final KPacSetService kPacSetService;
    private final KPacService kPacService;

    public KPacSetController(KPacSetService kPacSetService, KPacService kPacService) {
        this.kPacSetService = kPacSetService;
        this.kPacService = kPacService;
    }

    @GetMapping
    public String findAllKPacSets(Model model) throws JsonProcessingException {
        List<KnowledgePacSet> knowledgePacs = kPacSetService.findAll();
        ObjectMapper mapper = new ObjectMapper();

        String kpacsets = mapper.writeValueAsString(knowledgePacs);
        model.addAttribute("kpacsets", kpacsets);

        String kpacs = mapper.writeValueAsString(kPacService.findAll());
        model.addAttribute("kpacs", kpacs);

        return "kpacset";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) throws JsonProcessingException {
        KnowledgePacSet knowledgePacs = kPacSetService.findById(id);
        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(knowledgePacs);
        model.addAttribute("kpacset", result);

        return "kpacs_in_set";
    }

    @PostMapping
    public String create(@RequestBody KnowledgePacSetDto knowledgePacSetDto) {
        List<KnowledgePac> knowledgePacSets = List.of(knowledgePacSetDto.getArr());
        KnowledgePacSet pacSet = new KnowledgePacSet(knowledgePacSetDto.getTitle(),
                knowledgePacSets);

        kPacSetService.create(pacSet);
        return "redirect:/sets";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        kPacSetService.delete(id);

        return ResponseEntity.ok("Item deleted successfully");
    }

}
