package com.trnqb.cafe;

import com.trnqb.cafe.dto.Role;
import com.trnqb.cafe.entity.User;
import com.trnqb.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CafeManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CafeManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = userRepository.findByRole(Role.ADMIN);

        if (null == admin) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin123456"));
            user.setName("admin");
            user.setRole(Role.ADMIN);
            user.setPhoneNumber("9987654321");
            user.setStatus("true");
            userRepository.save(user);
        }
    }
}
