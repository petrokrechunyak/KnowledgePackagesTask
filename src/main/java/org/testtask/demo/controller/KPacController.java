package org.testtask.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.testtask.demo.dto.KnowledgePacDto;
import org.testtask.demo.entity.KnowledgePac;
import org.testtask.demo.service.KPacService;

import java.util.List;

@Controller
@RequestMapping("/kpacs")
public class KPacController {

    @Autowired
    private KPacService kPacService;

    @GetMapping
    public String getAllKPacs(Model model) throws JsonProcessingException {
        List<KnowledgePac> knowledgePacs = kPacService.findAll();
        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writeValueAsString(knowledgePacs);
        model.addAttribute("kpacs", result);
        model.addAttribute("set", false);

        return "kpacs";
    }

    @PostMapping
    public String createItem(@RequestBody KnowledgePacDto knowledgePacDto) {
        KnowledgePac knowledgePac = new KnowledgePac(knowledgePacDto.getTitle(),
                knowledgePacDto.getDescription());
        kPacService.create(knowledgePac);
        return "redirect:/kpacs/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        kPacService.delete(id);

        return ResponseEntity.ok("Item deleted successfully");
    }

}
