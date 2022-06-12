//package hcmus.nmq.simplaneservice.services;
//
//import hcmus.nmq.entities.User;
//import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
//import hcmus.nmq.simplaneservice.services.impls.BaseService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//
///**
// * 6:02 PM 6/11/2022
// * LeHongQuan
// */
//
//public class UserService extends BaseService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new SimplaneServiceException("Tài khoản không tồn tại! Vui lòng kiểm tra lại!");
//        }
//        return new CustomUserDetails(user);
//    }
//}