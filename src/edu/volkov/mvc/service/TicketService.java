package edu.volkov.mvc.service;

import edu.volkov.mvc.dao.TicketDao;
import edu.volkov.mvc.dto.TicketDto;
import edu.volkov.mvc.entity.Ticket;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketService {

    private static final TicketService INSTANCE = new TicketService();

    public static TicketService getInstance() {
        return INSTANCE;
    }

    private final TicketDao ticketDao = TicketDao.getInstance();

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(this::asDto)
                .collect(Collectors.toList());
    }

    private TicketDto asDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .flightId(ticket.getFlightId())
                .seatNo(ticket.getSeatNo())
                .build();
    }
}
