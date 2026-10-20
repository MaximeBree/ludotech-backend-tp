package com.polytech.ludotech.dto;

import com.polytech.ludotech.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {

    private UUID id;
    private String name;
    private int number_players;
    private int time;
    private Status status;
}
