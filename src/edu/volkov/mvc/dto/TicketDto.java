package edu.volkov.mvc.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TicketDto {

    Long id;
    Long flightId;
    String seatNo;
}
