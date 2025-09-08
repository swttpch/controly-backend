package br.com.controly.controllers;


import br.com.controly.dtos.UserDTO;
import br.com.controly.viewmodels.UserProfileViewModel;
import br.com.controly.domain.entities.Users;
import br.com.controly.dtos.NewUserDTO;
import br.com.controly.services.ProfileService;
import br.com.controly.services.UserService;
import br.com.controly.dtos.PasswordRecoveryDTO;
import br.com.controly.services.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired  
    private PasswordRecoveryService passwordRecoveryService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }
    @GetMapping
    public ResponseEntity<List<Users>> getListOfUsers() {
        List<Users> users =  userService.getListOfUsers();
        return users.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<Users> createNewUser(@RequestBody @Valid NewUserDTO user) {
        Users newUser = userService.createNewUser(user);
        return ResponseEntity.status(201).body(newUser);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        int usersDeleted = userService.deleteUserById(id);
        if (usersDeleted == 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already disabled.");
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsersInfo(@PathVariable Long id,
                                                  @RequestBody @Valid UserDTO form) {
        if(userService.updateUsersInfo(id, form)!= 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong.");
        return ResponseEntity.status(200).body("User's data updated.");
    }

    @PutMapping("/mobile/{id}")
    public ResponseEntity<Users> updateUsersInfoMobile(@PathVariable Long id,
                                                       @RequestBody @Valid UserDTO form) {
        Users user = userService.updateUsersInfoMobile(id, form);
        if(user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong.");
        return ResponseEntity.status(200).body(user);
    }


    @PostMapping("/password-recovery")
    public ResponseEntity<?> passwordRecovery(@RequestBody PasswordRecoveryDTO form) {
        passwordRecoveryService.passwordRecovery(form);
        return ResponseEntity.status(200).body("New password sent to users e-mail.");
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Integer> verifyIfEmailExists(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.verifyIfEmailExists(email));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileViewModel> getProfileByUserId(@PathVariable long id) {
        UserProfileViewModel user = profileService.getUserProfile(id);
        return ResponseEntity.status(200).body(user);
    }
}
