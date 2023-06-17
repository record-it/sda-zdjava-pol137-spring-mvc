package pl.sda.zdjavapol137.mvcspringquiz.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.zdjavapol137.mvcspringquiz.model.AppUser;
import pl.sda.zdjavapol137.mvcspringquiz.repository.AppUserRepository;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<AppUser> optionalAppUser = userRepository.findByEmailAddress(username);
//        if (optionalAppUser.isEmpty()){
//            throw new UsernameNotFoundException("Not valid username or password!");
//        }
//        return optionalAppUser.get();
        return optionalAppUser.orElseThrow(() -> new UsernameNotFoundException("Not valid username or password!"));
    }
}
