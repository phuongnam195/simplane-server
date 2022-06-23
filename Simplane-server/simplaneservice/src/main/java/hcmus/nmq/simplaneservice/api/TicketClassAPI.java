package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.TicketClass;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 11:40 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.TICKET_CLASS_SERVICE_URL)
public class TicketClassAPI extends BaseAPI {
    @RequiredHeaderToken
    @Operation(summary = "Tạo ticket class")
    @PostMapping
    public TicketClass createAirport(@RequestBody TicketClass ticketClass) {
//        String id = sequenceNumberRepository.getSequence(TicketClass.class);
//        ticketClass.setId(id);
        ticketClassRepository.save(ticketClass);
        return ticketClass;
    }

    @Operation(summary = "Danh sách ticket class")
    @GetMapping()
    public ListWrapper<TicketClass> getTicketClass() {
        List<TicketClass> ticketClasses = ticketClassRepository.findAll();
        return ListWrapper.<TicketClass>builder()
                .data(ticketClasses).build();
    }
}