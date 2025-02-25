package com.example.skyfast_2_0.service;
import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.dto.TicketInfoDTO;
import com.example.skyfast_2_0.entity.*;
import com.example.skyfast_2_0.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.skyfast_2_0.repository.BookingRepository;
import com.example.skyfast_2_0.repository.SeatRepository;
import com.example.skyfast_2_0.repository.FlightRepository;
import com.example.skyfast_2_0.repository.PassengerRepository;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository,
                         BookingRepository bookingRepository,
                         SeatRepository seatRepository,
                         FlightRepository flightRepository,
                         PassengerRepository passengerRepository,
                         ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
    }

    public List<TicketInfoDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticket -> {
            TicketInfoDTO dto = new TicketInfoDTO();
            dto.setId(ticket.getId());
            dto.setStatus(ticket.getStatus());
            dto.setTicketPrice(ticket.getTicketPrice());

            // Lấy bookingId từ đối tượng booking nếu không null
            if(ticket.getBooking() != null) {
                dto.setBookingId(ticket.getBooking().getId());
            }

            // Lấy các trường từ các đối tượng quan hệ
            dto.setFlightNumber(ticket.getFlight().getFlightNumber());
            dto.setSeatNumber(ticket.getSeat().getSeatNumber());
            dto.setPassengerFullName(ticket.getPassenger().getFullName());
            return dto;
        }).collect(Collectors.toList());
    }
    public TicketInfoDTO getTicketById(Integer id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            TicketInfoDTO dto = new TicketInfoDTO();
            dto.setId(ticket.getId());
            dto.setStatus(ticket.getStatus());
            dto.setTicketPrice(ticket.getTicketPrice());
            if (ticket.getBooking() != null) {
                dto.setBookingId(ticket.getBooking().getId());
            }
            dto.setFlightNumber(ticket.getFlight().getFlightNumber());
            dto.setSeatNumber(ticket.getSeat().getSeatNumber());
            dto.setPassengerFullName(ticket.getPassenger().getFullName());
            return dto;
        }
        return null;
    }

    public TicketInfoDTO updateTicket(Integer id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        // Cập nhật các field đơn giản
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setTicketPrice(ticketDTO.getTicketPrice());

        // Cập nhật quan hệ nếu có thay đổi (đảm bảo rằng các repository này được tiêm vào Service)
        if (ticketDTO.getBookingId() != 0) { // hoặc kiểm tra != null nếu dùng Integer
            Booking booking = bookingRepository.findById(ticketDTO.getBookingId())
                    .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
            ticket.setBooking(booking);
        }
        if (ticketDTO.getSeatId() != 0) {
            Seat seat = seatRepository.findById(ticketDTO.getSeatId())
                    .orElseThrow(() -> new EntityNotFoundException("Seat not found"));
            ticket.setSeat(seat);
        }
        if (ticketDTO.getFlightId() != 0) {
            Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                    .orElseThrow(() -> new EntityNotFoundException("Flight not found"));
            ticket.setFlight(flight);
        }
        if (ticketDTO.getPassengerId() != 0) {
            Passenger passenger = passengerRepository.findById(ticketDTO.getPassengerId())
                    .orElseThrow(() -> new EntityNotFoundException("Passenger not found"));
            ticket.setPassenger(passenger);
        }

        ticketRepository.save(ticket);

        // Mapping kết quả trả về
        TicketInfoDTO dto = new TicketInfoDTO();
        dto.setId(ticket.getId());
        dto.setStatus(ticket.getStatus());
        dto.setTicketPrice(ticket.getTicketPrice());
        if (ticket.getBooking() != null) {
            dto.setBookingId(ticket.getBooking().getId());
        }
        dto.setFlightNumber(ticket.getFlight().getFlightNumber());
        dto.setSeatNumber(ticket.getSeat().getSeatNumber());
        dto.setPassengerFullName(ticket.getPassenger().getFullName());
        return dto;
    }

}
