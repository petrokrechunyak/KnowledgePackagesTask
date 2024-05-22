package org.testtask.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgePacSet {

    private Long id;
    private String title;

    private List<KnowledgePac> kPacs;

    public KnowledgePacSet(String title, List<KnowledgePac> kPacs) {
        this.title = title;
        this.kPacs = kPacs;
    }
}
