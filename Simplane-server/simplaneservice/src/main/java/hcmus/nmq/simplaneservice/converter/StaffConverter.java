package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.entities.Ticket;
import hcmus.nmq.entities.User;
import hcmus.nmq.model.dtos.StaffDTO;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 10:41 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class StaffConverter {
    public List<StaffDTO> toDTO(List<User> users, List<Ticket> tickets) {
        List<StaffDTO> staffDTOS = new ArrayList<>();
        users.forEach(user -> {
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setId(user.getId());
            staffDTO.setUsername(user.getUsername());
            staffDTO.setFullname(user.getFullname());
            List<Ticket> ticketUser = tickets.stream().filter(t -> t.getIdPassenger().equals(user.getId())).collect(Collectors.toList());
            staffDTO.setTotalTicket(0.0);
            staffDTO.setTotalRevenue(0.0);
            if (ticketUser != null && ticketUser.size() > 0) {
                Double totalTicket = Double.valueOf(ticketUser.size());
                staffDTO.setTotalTicket(totalTicket);
                Double totalRevenue = ticketUser.stream().map(Ticket::getPrice).reduce(0.0,Double::sum);
                staffDTO.setTotalRevenue(totalRevenue);
            }
            staffDTOS.add(staffDTO);
        });

        return staffDTOS;
    }
}