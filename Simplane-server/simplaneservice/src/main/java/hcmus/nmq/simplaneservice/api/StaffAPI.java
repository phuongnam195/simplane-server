package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.dtos.StaffDTO;
import hcmus.nmq.model.search.ParameterSearchUser;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 10:20 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.STAFF_SERVICE_URL)
@Tag(name = "Staff", description = "Staff API")
public class StaffAPI extends BaseAPI {
    @Operation(summary = "Danh sách Staff")
    @GetMapping()
    public ListWrapper<StaffDTO> getStaffProfile(@RequestParam(value = "id", required = false) String id,
                                                 @RequestParam(value = "username", required = false) String username) {
        ParameterSearchUser parameterSearchUser = new ParameterSearchUser();
        if (!id.isBlankOrNull()) {
            parameterSearchUser.setId(id);
        }
        if (!username.isBlankOrNull()) {
            parameterSearchUser.setUsername(username);
        }
        ListWrapper<StaffDTO> listWrapper = userService.searchStaffs(parameterSearchUser);
        return listWrapper;
    }
}