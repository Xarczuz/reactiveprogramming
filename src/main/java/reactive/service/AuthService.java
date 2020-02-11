package reactive.service;

import org.springframework.stereotype.Service;
import reactive.PasswordHash;
import reactive.UserRepository;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<Boolean> validateUsernameAndPassword(String username, String password) {
        return Mono.justOrEmpty(userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(PasswordHash.hashPassword(password, user.getSalt()).orElseThrow())));
    }
}
