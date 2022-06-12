package hcmus.nmq.simplaneservice.repositories.impls;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.repositories.IFlightRepositoryCustom;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 11:59 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public class IFlightRepositoryImpl extends BaseRepositoryCustom implements IFlightRepositoryCustom {

    @Override
    public ListWrapper<Flight> searchProduct(ParameterSearchFlight parameterSearchFlight) {
        Collection<String> flightIds = null;
        Collection<String> excludeFlightIds = null;
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("deleted").ne(true));
        if (!parameterSearchFlight.getFlightId().isBlankOrNull()) {
            flightIds.merge(Collections.singleton(parameterSearchFlight.getFlightId()));
        }
        if(!parameterSearchFlight.getFromAirportCode().isBlankOrNull()){
            criteria.add(Criteria.where("fromAirportCode").is(parameterSearchFlight.getFromAirportCode().trim()));
        }
        if(!parameterSearchFlight.getToAirportCode().isBlankOrNull()){
            criteria.add(Criteria.where("toAirportCode").is(parameterSearchFlight.getToAirportCode().trim()));
        }
        if (null != parameterSearchFlight.getFromDate()) {
            criteria.add(Criteria.where("dateTime").gte(parameterSearchFlight.getFromDate()));
        }
        if (null != parameterSearchFlight.getToDate()) {
            criteria.add(Criteria.where("dateTime").lte(parameterSearchFlight.getToDate()));
        }
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(criteria));
        if (parameterSearchFlight.getMaxResult() == null) {
            return ListWrapper.<Flight>builder()
                    .data(mongoTemplate.find(query, Flight.class))
                    .build();
        }
        long totalResult;
        if (parameterSearchFlight.getStartIndex() >= 0) {
            query.skip(parameterSearchFlight.getStartIndex());
        }
        if (parameterSearchFlight.getMaxResult() > 0) {
            query.limit(parameterSearchFlight.getMaxResult());
        }
        List<Flight> flights = mongoTemplate.find(query, Flight.class);
        totalResult = flights.size();
        return ListWrapper.<Flight>builder()
                .total(totalResult)
                .totalPage((totalResult - 1) / parameterSearchFlight.getMaxResult() + 1)
                .currentPage(parameterSearchFlight.getStartIndex() / (parameterSearchFlight.getMaxResult() + 1))
                .data(mongoTemplate.find(query, Flight.class))
                .build();
    }
}