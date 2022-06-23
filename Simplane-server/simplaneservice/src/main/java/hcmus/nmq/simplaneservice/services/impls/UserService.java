package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.Ticket;
import hcmus.nmq.entities.User;
import hcmus.nmq.model.dtos.StaffDTO;
import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.model.search.ParameterSearchUser;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.services.IUserService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 10:28 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class UserService extends BaseService implements IUserService {

    @Override
    public ListWrapper<StaffDTO> searchStaffs(ParameterSearchUser parameterSearchUser) {
        ListWrapper<User> userListWrapper = userRepository.searchUser(parameterSearchUser);
        List<User> users = userListWrapper.getData();
        List<String> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        List<Ticket> tickets = ticketRepository.findAllByIdPassengerIn(userIds);
        List<StaffDTO> staffDTOS = staffConverter.toDTO(users,tickets);
        return ListWrapper.<StaffDTO>builder()
                .data(staffDTOS)
                .build();
    }
}