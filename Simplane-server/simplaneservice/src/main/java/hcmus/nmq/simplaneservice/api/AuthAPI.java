package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.model.auth.LoginDTO;
import hcmus.nmq.model.wrapper.ObjectResponseWrapper;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * 2:12 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.AUTH_SERVICE_URL)
@Tag(name = "Auth", description = "Auth API")
public class AuthAPI extends BaseAPI{
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ObjectResponseWrapper login(@RequestBody LoginDTO login) {
        return ObjectResponseWrapper.builder()
                .status(1)
                .message("ok")
                .data("test")
                .build();
    }
}