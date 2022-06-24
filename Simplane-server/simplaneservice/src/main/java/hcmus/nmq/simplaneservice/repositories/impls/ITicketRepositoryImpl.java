package hcmus.nmq.simplaneservice.repositories.impls;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.entities.Ticket;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.repositories.IFlightRepositoryCustom;
import hcmus.nmq.simplaneservice.repositories.ITicketRepositoryCustom;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 1:14 AM 6/21/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public class ITicketRepositoryImpl extends BaseRepositoryCustom implements ITicketRepositoryCustom {

    @Override
    public ListWrapper<Ticket> searchTicket(ParameterSearchTicket parameterSearchTicket) {
        Collection<String> ticketIds = null;
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("deleted").ne(true));
        if (!parameterSearchTicket.getId().isBlankOrNull()) {
            ticketIds = ticketIds.merge(Collections.singleton(parameterSearchTicket.getId()));
        }
        if (null != parameterSearchTicket.getFromDate()) {
            criteria.add(Criteria.where("flightDate").gte(parameterSearchTicket.getFromDate()));
        }
        if (null != parameterSearchTicket.getToDate()) {
            criteria.add(Criteria.where("flightDate").lte(parameterSearchTicket.getToDate()));
        }
        if (!parameterSearchTicket.getFlightCode().isBlankOrNull()) {
            criteria.add(Criteria.where("code").is(parameterSearchTicket.getFlightCode().trim()));
        }
        if (ticketIds != null) {
            criteria.add(Criteria.where("_id").in(ticketIds));
        }
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(criteria));
        if (parameterSearchTicket.getMaxResult() == null) {
            return ListWrapper.<Ticket>builder()
                    .data(mongoTemplate.find(query, Ticket.class))
                    .build();
        }
        long totalResult;
        if (parameterSearchTicket.getStartIndex() >= 0) {
            query.skip(parameterSearchTicket.getStartIndex());
        }
        if (parameterSearchTicket.getMaxResult() > 0) {
            query.limit(parameterSearchTicket.getMaxResult());
        }
        List<Ticket> tickets = mongoTemplate.find(query, Ticket.class);
        totalResult = tickets.size();
        return ListWrapper.<Ticket>builder()
                .data(mongoTemplate.find(query, Ticket.class))
                .build();

    }
}