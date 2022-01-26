package edu.volkov.mvc.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FlightDto {

    Long id;
    String description;
}
