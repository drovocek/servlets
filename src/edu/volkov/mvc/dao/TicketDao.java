package edu.volkov.mvc.dao;

import edu.volkov.mvc.util.DaoConnectionManager;
import lombok.SneakyThrows;
import edu.volkov.mvc.entity.Ticket;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private final static String FIND_ALL_BY_FLIGHT_ID = """
            SELECT * FROM ticket WHERE flight_id=?
            """;

    private final static TicketDao INSTANCE = new TicketDao();

    private TicketDao() {
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public List<Ticket> findAllByFlightId(Long flightId) {
        try (var connection = DaoConnectionManager.getConnection();
             var statement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)) {
            statement.setObject(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        }
    }

    @SneakyThrows
    private Ticket buildTicket(ResultSet resultSet) {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }

    @Override
    public List<Ticket> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public void update(Ticket persist) {

    }

    @Override
    public Ticket save(Ticket persist) {
        return null;
    }
}
