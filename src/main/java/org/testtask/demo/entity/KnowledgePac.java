package org.testtask.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgePac {

    private Long id;
    private String title;
    private String description;
    private String creationDate;

    private List<KnowledgePacSet> sets;

    public KnowledgePac(String title, String description) {
        this.title = title;
        this.description = description;
        sets = new ArrayList<>();
    }
}
