package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.User;
import hcmus.nmq.model.auth.LoginDTO;
import hcmus.nmq.model.dtos.UserDTO;
import hcmus.nmq.model.wrapper.ObjectResponseWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.jwt.JwtTokenProvider;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 2:12 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.AUTH_SERVICE_URL)
@Tag(name = "Auth", description = "Auth API")
public class AuthAPI extends BaseAPI {
    @PermitAll
    @PostMapping(value = "/sign-in")
    public ObjectResponseWrapper login(@RequestBody LoginDTO login) {
        validateLoginUser(login);

        String token = JwtTokenProvider.generateToken(login.getUsername());
        User user = userRepository.findByUsername(login.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("fullname", user.getFullname());
        map.put("isVerified", user.isVerified());
        map.put("accessToken", token);
        map.put("isAdmin", user.isAdmin());
        return ObjectResponseWrapper.builder()
                .statusCode(200)
                .data(map)
                .build();
    }

    @PostMapping(value = "/sign-out")
    public ObjectResponseWrapper logout(@RequestParam String username) {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SimplaneServiceException("Có lỗi xảy ra! Vui lòng thử lại! " + e.getMessage());
        }

        if (user == null) {
            throw new SimplaneServiceException("Bạn chưa đăng nhập!");
        }
        if (!user.getUsername().equals(username)) {
            throw new SimplaneServiceException("Có lỗi xảy ra! Vui lòng thử lại!");
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return ObjectResponseWrapper.builder()
                .statusCode(200)
                .build();

    }

    @PermitAll
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
                .isVerified(userDTO.getIsVerified())
                .isAdmin(userDTO.getIsAdmin())
                .build();
        userRepository.save(user);
        String token = JwtTokenProvider.generateToken(userDTO.getUsername());
        userDTO.setAccessToken(token);
        return userDTO;
    }

    @RequiredHeaderToken
    @PutMapping(value = "/update-user")
    public UserDTO updateAccount(@RequestBody UserDTO userUpdate) {
        isAdmin();
        User user = validateUpdateUser(userUpdate);
        user = userRepository.findByUsername(userUpdate.getUsername());
        user.setAdmin(userUpdate.getIsAdmin());
        user.setFullname(userUpdate.getFullname());
        String password = passwordEncoder.encode(userUpdate.getPassword());
        user.setPassword(password);
        user.setVerified(userUpdate.getIsVerified());
        userRepository.save(user);
        return userUpdate;
    }

    @RequiredHeaderToken
    @DeleteMapping(value = "/{id}")
    public ObjectResponseWrapper deleteUser(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new SimplaneServiceException("Không tồn tại người dùng! Vui lòng kiểm tra lại!");
        }
        user.ifPresent(u -> {
            userRepository.deleteById(id);
        });
        return ObjectResponseWrapper.builder().data("delete").statusCode(200).build();
    }


    public User validateUpdateUser(UserDTO userDTO) {
        if (userDTO.getFullname().isBlankOrNull()) {
            throw new SimplaneServiceException("Tên không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getUsername().isBlankOrNull()) {
            throw new SimplaneServiceException("Tài khoản không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getPassword().isBlankOrNull()) {
            throw new SimplaneServiceException("Mật khẩu không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getIsAdmin() == null) {
            throw new SimplaneServiceException("Role người dùng không được bỏ trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getIsVerified() == null) {
            throw new SimplaneServiceException("Xác nhận tài khoản không được bỏ trống! Vui lòng kiểm tra lại!");
        }

        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new SimplaneServiceException("Tài khoản không tồn tại! Vui lòng kiểm tra lại!");
        }
        return user;

    }

    public void validateCreateUser(UserDTO userDTO) {
        if (userDTO.getFullname().isBlankOrNull()) {
            throw new SimplaneServiceException("Tên không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getUsername().isBlankOrNull()) {
            throw new SimplaneServiceException("Tài khoản không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getPassword().isBlankOrNull()) {
            throw new SimplaneServiceException("Mật khẩu không được để trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getIsAdmin() == null) {
            throw new SimplaneServiceException("Role người dùng không được bỏ trống! Vui lòng kiểm tra lại!");
        }
        if (userDTO.getIsVerified() == null) {
            throw new SimplaneServiceException("Xác nhận tài khoản không được bỏ trống! Vui lòng kiểm tra lại!");
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
        if (!user.isVerified()) {
            throw new SimplaneServiceException("Tài khoản chưa xác thực! Vui lòng kiểm tra lại!");
        }
    }
}