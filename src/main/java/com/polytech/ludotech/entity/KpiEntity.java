package com.polytech.ludotech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kpi")
public class KpiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nb_livre_emprunte")
    private int nbLivreEmprunte;

    @Column(name = "nb_livre_dispo")
    private int nbLivreDispo;

    @Column(name = "nb_film_emprunte")
    private int nbFilmEmprunte;

    @Column(name = "nb_film_dispo")
    private int nbFilmDispo;

    @Column(name = "nb_jeu_emprunte")
    private int nbJeuEmprunte;

    @Column(name = "nb_jeu_dispo")
    private int nbJeuDispo;
}
