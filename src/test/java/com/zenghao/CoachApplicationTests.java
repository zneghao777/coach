package com.zenghao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CoachApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("zenghao1234");
        boolean matches = passwordEncoder.matches("zenghao1234", "$2a$10$knLGbjJ/VzIcRQB.jHeMVuGVZzh7FiUfyCBtwk7yhdb68qu3EMPBS");
        System.out.println(encode);
        System.out.println(matches);
    }

}
