package com.polytech.ludotech.mappers;

import com.polytech.ludotech.dto.KpiDTO;
import com.polytech.ludotech.entity.KpiEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KpiMapper {

    KpiDTO toDTO(KpiEntity kpiEntity);
}
