package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.entities.FlightAttribute;
import hcmus.nmq.entities.Ticket;
import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.EnumConst;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * 8:50 PM 6/20/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.TICKET_SERVICE_URL)
@Tag(name = "Ticket", description = "Ticket API")
public class TicketAPI extends BaseAPI {

    @RequiredHeaderToken
    @Operation(summary = "Tạo vé máy bay")
    @PostMapping()
    public TicketDTO createTicket(@RequestBody TicketDTO ticketModel) {
        validateCreateTicket(ticketModel);
        String id = ticketService.saveTicket(ticketModel);
        Optional<Ticket> ticketOp = ticketRepository.findById(id);
        Ticket ticket = ticketOp.get();
//        return TicketDTO.builder()
//                .id(id)
//                .bookedTime(ticket.getBookedTime().getTime())
//                .flightCode(ticket.getCode())
//                .flightDate(ticket.getFlightDate().getTime())
//                .price(ticket.getPrice())
//                .ticketClassId(ticket.getIdTicketClass())
//                .idUser(ticket.getIdUser())
//                .build();
        return ticketConverter.toDTO(ticket);
    }

    @Operation(summary = "Lấy danh sách vé máy bay")
    @GetMapping()
    public ListWrapper<TicketDTO> getListTicket(@RequestParam(value = "id", required = false) String id,
                                                @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'") Date fromDate,
                                                @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'") Date toDate,
                                                @RequestParam(value = "flightCode", required = false) String flightCode,
                                                @RequestParam(value = "currentPage", required = false) @Min(value = 1, message = "currentPage phải lớn hơn 0") @Parameter(description = "Default: 1") Integer currentPage,
                                                @RequestParam(value = "maxResult", required = false) @Min(value = 1, message = "maxResult phải lớn hơn 0") @Max(value = 50, message = "maxResult phải bé hơn 50") @Parameter(description = "Default: 50; Size range: 1-50") Integer maxResult) {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (maxResult == null || maxResult == 0) {
            maxResult = 50;
        }
        Long startIndex = (long) ((currentPage - 1) * maxResult);
        ParameterSearchTicket parameterSearchTicket = new ParameterSearchTicket();
        if (null != fromDate) {
            parameterSearchTicket.setFromDate(fromDate);
        }
        if (null != toDate) {
            parameterSearchTicket.setToDate(toDate);
        }
        if (!flightCode.isBlankOrNull()) {
            parameterSearchTicket.setFlightCode(flightCode);
        }
        if (!id.isBlankOrNull()) {
            parameterSearchTicket.setId(id);
        }

        parameterSearchTicket.setStartIndex(startIndex);
        parameterSearchTicket.setMaxResult(maxResult);
        ListWrapper<TicketDTO> listWrapper = ticketService.searchTicketDTOS(parameterSearchTicket);
        return ListWrapper.<TicketDTO>builder()
                .data(listWrapper.getData())
                .build();
    }

    @RequiredHeaderToken
    @Operation(summary = "Xóa vé máy bay")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable(value = "id", required = true) String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (!ticket.isPresent()) {
            throw new SimplaneServiceException("Không tồn tại vé có id: " + id + "!");
        }
        ticketService.deleteTicket(id);
    }

    private void validateCreateTicket(TicketDTO ticketDTO) {
        if (ticketDTO.getFlightCode().isBlankOrNull()) {
            throw new SimplaneServiceException("Vui lòng nhập flightCode!");
        }
        if (ticketDTO.getTicketClassId().isBlankOrNull()) {
            throw new SimplaneServiceException("Vui lòng nhập ticketClassId!");
        }
        Flight flight = flightRepository.findByCode(ticketDTO.getFlightCode());
        if (flight == null) {
            throw new SimplaneServiceException("FlightCode bị sai! Vui lòng nhập lại FlightCode!");
        }
        Map<String, FlightAttribute> mapSeat = flightAttrService.getMapTypeAttrByFlightId(flight.getId(), EnumConst.TypeAttrFlight.SEAT.toString());
        FlightAttribute seatAttr = mapSeat.get(ticketDTO.getTicketClassId());
        if (seatAttr.getAmount() == 0) {
            throw new SimplaneServiceException("Vé này đã hết chỗ đặt! Vui lòng kiểm tra lại!");
        }
//        Map<String, FlightAttribute> mapBook = flightAttrService.getMapTypeAttrByFlightId(flight.getId(), EnumConst.TypeAttrFlight.BOOK.toString());
//        FlightAttribute bookAttr = mapBook.get(ticketDTO.getTicketClassId());

    }

}