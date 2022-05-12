package com.example.demo.registracija;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registracija")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    
    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){

        return registrationService.register(request);
    }
    
    @GetMapping("{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId){
        AppUser user = appUserRepository.findById(userId).
                orElseThrow(() ->
                        new IllegalStateException("Korisnik sa userId " + userId + " ne postoji"));
                return ResponseEntity.ok(user);
    }

    @PutMapping("{userId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long userId,@RequestBody AppUser appUserDetails){
        AppUser updateUser = appUserRepository.findById(userId).
                orElseThrow(() ->
                        new IllegalStateException("Korisnik sa userId " + userId + " ne postoji"));
        updateUser.setFirstname(appUserDetails.getFirstname());
        updateUser.setLastname(appUserDetails.getLastname());
        updateUser.setEmail(appUserDetails.getEmail());

        appUserRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/registracija/{userId}")
    @ResponseBody
    public void deleteTopic(@PathVariable Long userId){
        RegistrationService.deleteTopic(userId);
    }
}
