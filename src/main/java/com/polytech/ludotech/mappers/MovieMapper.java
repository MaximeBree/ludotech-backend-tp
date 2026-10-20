package com.polytech.ludotech.mappers;

import com.polytech.ludotech.dto.MovieDTO;
import com.polytech.ludotech.entity.MovieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(MovieEntity movieEntity);

    MovieEntity toEntity(MovieDTO movieDTO);

    List<MovieDTO> toDTO(List<MovieEntity> movieEntities);
}
