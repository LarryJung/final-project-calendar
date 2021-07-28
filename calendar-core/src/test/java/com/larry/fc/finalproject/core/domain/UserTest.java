package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.util.Encryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Larry
 */
public class UserTest {

    private final Encryptor alwaysTrueEncryptor = new Encryptor() {
        @Override
        public String encrypt(String origin) {
            return origin;
        }

        @Override
        public boolean isMatch(String origin, String hashed) {
            return true;
        }
    };

    @Test
    void isMatchedTest() {
        final User user = User.builder().name("name").password("pw").email("email").build();
        assertTrue(user.isMatched(alwaysTrueEncryptor, "aaa"));
    }
}
