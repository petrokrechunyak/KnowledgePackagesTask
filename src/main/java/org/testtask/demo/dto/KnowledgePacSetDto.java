package org.testtask.demo.dto;

import lombok.Data;
import org.testtask.demo.entity.KnowledgePac;

@Data
public class KnowledgePacSetDto {
    private Long id;
    private String title;

    private KnowledgePac[] arr;

}
