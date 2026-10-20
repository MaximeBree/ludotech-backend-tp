package com.polytech.ludotech.mappers;

import com.polytech.ludotech.dto.GameDTO;
import com.polytech.ludotech.entity.GameEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDTO toDTO(GameEntity gameEntity);

    GameEntity toEntity(GameDTO gameDTO);

    List<GameDTO> toDTO(List<GameEntity> gameEntities);
}
