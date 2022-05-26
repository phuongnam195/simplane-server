package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.simplaneservice.repositories.IUserRepository;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.logging.Logger;

/**
 * 1:39 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public class BaseAPI {
    protected static final Logger logger = Logger.getLogger(BaseAPI.class.getName());

    @Autowired
    protected IUserRepository userRepository;

}