package hcmus.nmq.simplaneservice.services;

import hcmus.nmq.model.dtos.StaffDTO;
import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.search.ParameterSearchUser;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface IUserService {
    ListWrapper<StaffDTO> searchStaffs(ParameterSearchUser parameterSearchUser);
}
