package com.example.skyfast_2_0.service;

import com.example.skyfast_2_0.dto.TicketDTO;
import com.example.skyfast_2_0.dto.UserDTO;
import com.example.skyfast_2_0.entity.Ticket;
import com.example.skyfast_2_0.entity.User;
import com.example.skyfast_2_0.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
    public TicketDTO getTicketById(Integer id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.map(value -> modelMapper.map(value, TicketDTO.class)).orElse(null);
    }

    public TicketDTO updateTicket(Integer id, TicketDTO ticketDTO) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStatus(ticketDTO.getStatus());
            ticket.setTicketPrice(ticketDTO.getTicketPrice());
            ticketRepository.save(ticket);
            return modelMapper.map(ticket, TicketDTO.class);
        }
        return null;
    }
}
