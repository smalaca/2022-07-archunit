package com.smalaca.gtd.usermanagement.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserFactoryTest {
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserFactory factory = new UserFactory(passwordEncoder);

    @Test
    void shouldCreateUser() {
        given(passwordEncoder.encode("password")).willReturn("encoded-password");

        User actual = factory.create("peterparker", "password");

        UserAssertion.assertThat(actual)
                .hasUserName("peterparker")
                .hasPassword("encoded-password")
                .isActive()
                .hasUserRole();
    }
}