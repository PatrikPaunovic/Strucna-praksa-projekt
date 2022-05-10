package com.example.demo.registracija;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private static AppUserRepository appUserRepository;
    
    public static void deleteTopic(Long userId) {
        boolean exists = appUserRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("korisnik sa userId " + userId + " ne postoji");
        }
        appUserRepository.deleteById(userId);
    }
    
    public String register(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("email nije ispravan");
       }
       return appUserService.signUpUser(
               new AppUser(
                       request.getUsername(),
                       request.getFirstname(),
                       request.getLastname(),
                       request.getEmail(),
                       request.getPassword(),
                       AppUserRole.USER

               )
       );
    }
}
