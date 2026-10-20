package com.polytech.ludotech.service;

import com.polytech.ludotech.entity.KpiEntity;
import com.polytech.ludotech.entity.Status;
import com.polytech.ludotech.repository.KpiRepository;
import org.springframework.stereotype.Service;

@Service
public class KpiService {

    private final KpiRepository kpiRepository;

    public KpiService(KpiRepository kpiRepository) {
        this.kpiRepository = kpiRepository;
    }

    public KpiEntity getKpi() {
        //attention findAll renvoie une liste meme si 1 element
        return kpiRepository.findAll().get(0);
    }

    public void modifierLivre(Status status, int delta) {
        if (status == Status.BORROWED)
            kpiRepository.modifierLivreEmprunte(delta);
        else if (status == Status.AVAILABLE)
            kpiRepository.modifierLivreDispo(delta);
    }

    public void modifierFilm(Status status, int delta) {
        if (status == Status.BORROWED)
            kpiRepository.modifierFilmEmprunte(delta);
        else if (status == Status.AVAILABLE)
            kpiRepository.modifierFilmDispo(delta);
    }

    public void modifierJeu(Status status, int delta) {
        if (status == Status.BORROWED)
            kpiRepository.modifierJeuEmprunte(delta);
        else if (status == Status.AVAILABLE)
            kpiRepository.modifierJeuDispo(delta);
    }
}
