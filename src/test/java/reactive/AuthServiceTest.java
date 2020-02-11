package reactive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactive.service.AuthService;
import reactor.core.publisher.Mono;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Test
    void login_user_test() {
        Mono<Boolean> validatedMono = authService.validateUsernameAndPassword("testtest", "testtest");
        validatedMono.blockOptional().ifPresent(Assertions::assertTrue);

    }

    @Test
    void login_user_wrong_password() {
        Mono<Boolean> validatedMono = authService.validateUsernameAndPassword("testtest", "testtst");
        validatedMono.blockOptional().ifPresent(Assertions::assertFalse);
    }
    @Test
    void login_user_wrong_password2() {
        Mono<Boolean> validatedMono = authService.validateUsernameAndPassword("testtest", "te234sttst");
        validatedMono.blockOptional().ifPresent(Assertions::assertFalse);
    }
    @Test
    void login_user_wrong_password3() {
        Mono<Boolean> validatedMono = authService.validateUsernameAndPassword("testtest", "testt");
        validatedMono.blockOptional().ifPresent(Assertions::assertFalse);
    }

    @Test
    void login_user_wrong_username() {
        Mono<Boolean> validatedMono = authService.validateUsernameAndPassword("te", "testtst");
        assertTrue(validatedMono.blockOptional().isEmpty());
    }
}