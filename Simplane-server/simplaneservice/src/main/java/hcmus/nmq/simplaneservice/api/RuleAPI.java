package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.entities.Rule;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.model.wrapper.ListResponseWrapper;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.model.wrapper.ObjectResponseWrapper;
import hcmus.nmq.model.wrapper.ResponseWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 3:30 PM 6/24/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.RULE_SERVICE_URL)
@Tag(name = "Rule", description = "Rule API")
public class RuleAPI extends BaseAPI{
    @RequiredHeaderToken
    @Operation(summary = "Tạo rule")
    @PostMapping
    public Rule createRule(@RequestBody Rule rule) {
        isAdmin();
        String id = sequenceNumberRepository.getSequence(Rule.class);
        rule.setId(id);
        rule = ruleRepository.save(rule);
        return rule;
    }

    @Operation(summary = "Danh sách rule")
    @GetMapping
    public Rule getRules() {
        List<Rule> rules = ruleRepository.findAll();
        return rules.get(rules.size() - 1);
    }
}