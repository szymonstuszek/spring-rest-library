package com.kodilla.library.controller;

import com.kodilla.library.domain.Account;
import com.kodilla.library.domain.Role;
import com.kodilla.library.domain.enums.RoleName;
import com.kodilla.library.domain.request.RegisterRequest;
import com.kodilla.library.repository.AccountRepository;
import com.kodilla.library.repository.RoleRepository;
import com.kodilla.library.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/library", consumes = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class RegistrationController {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {

        if (accountRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity<>(
                    "Account with this email is already registered. Try a different email.",
                    HttpStatus.BAD_REQUEST
            );
        }

        //implement differently if there are more roles provided
        Account account = new Account(
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getUsername(),
                setupUserRole()
        );

        accountRepository.save(account);

        return new ResponseEntity<>("Account created successfully.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "register/setup")
    public void assignAccount(@RequestParam Long userId, @RequestParam Long accountId) {
        registrationService.setUserForAccount(accountId, userId);
    }

    private Set<Role> setupUserRole() {
        Set<Role> standardRoles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER);
        standardRoles.add(userRole);
        return standardRoles;
    }
}
