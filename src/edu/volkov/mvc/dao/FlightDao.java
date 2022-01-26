package edu.volkov.mvc.dao;

import edu.volkov.mvc.util.DaoConnectionManager;
import lombok.SneakyThrows;
import edu.volkov.mvc.entity.Flight;
import edu.volkov.mvc.entity.FlightStatus;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private final static FlightDao INSTANCE = new FlightDao();

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    private FlightDao() {
    }

    private static final String FIND_ALL = """
            SELECT * FROM flight
            """;

    @SneakyThrows
    @Override
    public List<Flight> findAll() {
        try (var connection = DaoConnectionManager.getConnection();
             var statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        }
    }

    @SneakyThrows
    private Flight buildFlight(ResultSet resultSet) {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight persist) {

    }

    @Override
    public Flight save(Flight persist) {
        return null;
    }
}
