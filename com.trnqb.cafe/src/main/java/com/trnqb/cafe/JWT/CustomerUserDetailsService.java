package com.trnqb.cafe.JWT;

import com.trnqb.cafe.dao.UserDao;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private com.trnqb.cafe.POJO.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);

        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public com.trnqb.cafe.POJO.User getUserDetail() {
        return userDetail;
    }
}
