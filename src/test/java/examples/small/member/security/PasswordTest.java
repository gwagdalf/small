package examples.small.member.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    @Test
    public void encodingTest() throws Exception{
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories
                        .createDelegatingPasswordEncoder();

        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("1234",
                "{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76");
        System.out.println(matches);
        Assert.assertTrue(matches);
    }
}
