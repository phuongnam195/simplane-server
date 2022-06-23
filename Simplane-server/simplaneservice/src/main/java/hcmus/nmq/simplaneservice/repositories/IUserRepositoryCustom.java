package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.User;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.search.ParameterSearchUser;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface IUserRepositoryCustom {
    ListWrapper<User> searchUser(ParameterSearchUser parameterSearchUser);
}
