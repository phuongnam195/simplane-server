package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 8:50 PM 6/20/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.TICKET_SERVICE_URL)
@Tag(name = "Ticket", description = "Ticket API")
public class TicketAPI extends BaseAPI {

//    @RequiredHeaderToken
//    @Operation(summary = "Tạo vé máy bay")
//    @PostMapping()
//    public TicketDTO createTicket()

}