package com.polytech.ludotech.controller;

import com.polytech.ludotech.dto.KpiDTO;
import com.polytech.ludotech.mappers.KpiMapper;
import com.polytech.ludotech.service.KpiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {

    private final KpiService kpiService;
    private final KpiMapper kpiMapper;

    public KpiController(KpiService kpiService, KpiMapper kpiMapper) {
        this.kpiService = kpiService;
        this.kpiMapper = kpiMapper;
    }

    @GetMapping
    public KpiDTO getKpi() {
        return kpiMapper.toDTO(kpiService.getKpi());
    }
}
