package hcmus.nmq.simplaneservice.repositories.impls;

import hcmus.nmq.entities.Ticket;
import hcmus.nmq.entities.User;
import hcmus.nmq.model.search.ParameterSearchUser;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.repositories.IUserRepositoryCustom;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 10:32 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public class IUserRepositoryCustomImpl extends BaseRepositoryCustom implements IUserRepositoryCustom {

    @Override
    public ListWrapper<User> searchUser(ParameterSearchUser parameterSearchUser) {
        Collection<String> userIds = null;
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("deleted").ne(true));
        if (!parameterSearchUser.getId().isBlankOrNull()) {
            userIds = userIds.merge(Collections.singleton(parameterSearchUser.getId()));
        }
        if (!parameterSearchUser.getUsername().isBlankOrNull()) {
            criteria.add(Criteria.where("username").is(parameterSearchUser.getUsername().trim()));
        }
        if (parameterSearchUser.getIsSearchStaff() != null && parameterSearchUser.getIsSearchStaff().equals(true)) {
            criteria.add(Criteria.where("isAdmin").is(false));
        }
        if (userIds != null) {
            criteria.add(Criteria.where("_id").in(userIds));
        }
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(criteria));
        if (parameterSearchUser.getMaxResult() == null) {
            return ListWrapper.<User>builder()
                    .data(mongoTemplate.find(query, User.class))
                    .build();
        }

        long totalResult;
        if (parameterSearchUser.getStartIndex() >= 0) {
            query.skip(parameterSearchUser.getStartIndex());
        }
        if (parameterSearchUser.getMaxResult() > 0) {
            query.limit(parameterSearchUser.getMaxResult());
        }
        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class);
        totalResult = tickets.size();
        return ListWrapper.<User>builder()
                .data(mongoTemplate.find(query, User.class))
                .build();
    }
}