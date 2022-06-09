package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static String USER_NOT_FOUND_MSG =
            "Korisnik sa računom %s nije pronađen";
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.
                findByEmail(appUser.getEmail())
                .isPresent();
        if(userExists){
            throw  new IllegalStateException("Uneseni email je u upotrebi");
        }

        appUserRepository.save(appUser);

        return "prolaz";
    }
}
