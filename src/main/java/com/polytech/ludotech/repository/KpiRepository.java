package com.polytech.ludotech.repository;

import com.polytech.ludotech.entity.KpiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KpiRepository extends JpaRepository<KpiEntity, UUID> {

    @Modifying
    @Query("update KpiEntity k set k.nbLivreEmprunte = k.nbLivreEmprunte + :delta")
    void modifierLivreEmprunte(int delta);

    @Modifying
    @Query("update KpiEntity k set k.nbLivreDispo = k.nbLivreDispo + :delta")
    void modifierLivreDispo(int delta);

    @Modifying
    @Query("update KpiEntity k set k.nbFilmEmprunte = k.nbFilmEmprunte + :delta")
    void modifierFilmEmprunte(int delta);

    @Modifying
    @Query("update KpiEntity k set k.nbFilmDispo = k.nbFilmDispo + :delta")
    void modifierFilmDispo(int delta);

    @Modifying
    @Query("update KpiEntity k set k.nbJeuEmprunte = k.nbJeuEmprunte + :delta")
    void modifierJeuEmprunte(int delta);

    @Modifying
    @Query("update KpiEntity k set k.nbJeuDispo = k.nbJeuDispo + :delta")
    void modifierJeuDispo(int delta);
}
