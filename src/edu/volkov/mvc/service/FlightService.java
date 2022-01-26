package edu.volkov.mvc.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import edu.volkov.mvc.dao.FlightDao;
import edu.volkov.mvc.dto.FlightDto;
import edu.volkov.mvc.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    public static FlightService getInstance() {
        return INSTANCE;
    }

    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(this::asDto)
                .collect(Collectors.toList());
    }

    private FlightDto asDto(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .description("""
                        %s-%s-%S 
                         """.formatted(
                        flight.getDepartureAirportCode(),
                        flight.getArrivalAirportCode(),
                        flight.getStatus()))
                .build();
    }
}
