package com.larry.fc.finalproject.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Larry
 */
public class EncryptorTest {

    @Test
    void bcryptTest() {
        final String origin = "my_password";
        final Encryptor encryptor = new BCryptEncryptor();
        final String hashed = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin, hashed));

        final String wrong = "my_passwordd";
        assertFalse(encryptor.isMatch(wrong, hashed));
    }
}
