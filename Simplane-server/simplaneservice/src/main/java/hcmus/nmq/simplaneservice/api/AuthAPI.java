package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.User;
import hcmus.nmq.model.auth.LoginDTO;
import hcmus.nmq.model.dtos.UserDTO;
import hcmus.nmq.model.wrapper.ObjectResponseWrapper;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.jwt.JwtTokenProvider;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.EnumConst;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2:12 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.AUTH_SERVICE_URL)
@Tag(name = "Auth", description = "Auth API")
@PermitAll
public class AuthAPI extends BaseAPI {

    @PostMapping(value = "/sign-in")
    public ObjectResponseWrapper login(@RequestBody LoginDTO login) {
        validateLoginUser(login);
        String token = JwtTokenProvider.generateToken(login.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return ObjectResponseWrapper.builder()
                .status(200)
                .message("Đăng nhập thành công")
                .data(map)
                .build();
    }

    @PostMapping(value = "/sign-up")
    public UserDTO createAccount(@RequestBody UserDTO userDTO) {
        validateCreateUser(userDTO);
        String id = sequenceNumberRepository.getSequence(User.class);
        String password = passwordEncoder.encode(userDTO.getPassword());
        User user = User.builder()
                .id(id)
                .fullname(userDTO.getFullname())
                .username(userDTO.getUsername())
                .password(password)
                .role(userDTO.getRole())
                .build();
        userRepository.save(user);
        return userDTO;
    }


    public void validateCreateUser(UserDTO userDTO) {
        List<String> roles = new ArrayList<>();
        roles.add(EnumConst.RoleUserEnum.USER.toString());
        roles.add(EnumConst.RoleUserEnum.MANAGER.toString());
        if (userDTO.getFullname().isBlankOrNull()) {
            throw new SimplaneServiceException("Tên không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getUsername().isBlankOrNull()) {
            throw new SimplaneServiceException("Tài khoản không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getPassword().isBlankOrNull()) {
            throw new SimplaneServiceException("Mật khẩu không được để trống! Vui lòng kiểm tra lại!");
        }
        if (!roles.contains(userDTO.getRole())) {
            throw new SimplaneServiceException("Role người dùng không đúng! Vui lòng kiểm tra lại!");
        }
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null) {
            throw new SimplaneServiceException("Tài khoản đã tồn tại! Vui lòng kiểm tra lại!");
        }
    }

    public void validateLoginUser(LoginDTO login) {
        String username = login.getUsername();
        String password = login.getPassword();
        if (username.isBlankOrNull()) {
            throw new SimplaneServiceException("Tài khoản không được để trống! Vui lòng kiểm tra lại!");
        }
        if (password.isBlankOrNull()) {
            throw new SimplaneServiceException("Mật khẩu không được để trống! Vui lòng kiểm tra lại!");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SimplaneServiceException("Tài khoản không tồn tại! Vui lòng kiểm tra lại!");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new SimplaneServiceException("Mật khẩu không đúng! Vui lòng kiểm tra lại!");
        }
    }
}