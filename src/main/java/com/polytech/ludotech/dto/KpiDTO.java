package com.polytech.ludotech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiDTO {

    private int nbLivreEmprunte;
    private int nbLivreDispo;
    private int nbFilmEmprunte;
    private int nbFilmDispo;
    private int nbJeuEmprunte;
    private int nbJeuDispo;
}
