package br.com.controly.services;

import br.com.controly.dtos.NewUserDTO;
import br.com.controly.viewmodels.UserViewModel;
import br.com.controly.dtos.UserDTO;
import br.com.controly.exception.EmailAlreadyExistsException;
import br.com.controly.exception.UsersIdNotFould;
import br.com.controly.mappers.UserMapper;
import br.com.controly.jpa.UserRepository;
import br.com.controly.domain.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    private  final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private  final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Transactional
    public Users createNewUser(NewUserDTO newUser) {
        Optional<Users> optionalUser = userRepository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) throw new EmailAlreadyExistsException();
        Users user = userMapper.toUserEntity(newUser);
        return userRepository.save(user);
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UsersIdNotFould::new);
    }

    public List<Users> getListOfUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public int deleteUserById(Long id) {
        Users user = this.getUserById(id);
        // if user is already disabled then return 0
        // otherwise, disable this user and return 1
        return 1;
    }


    public int updateUsersInfo(Long id, UserDTO form) {
        Users user = this.getUserById(id);
        userMapper.toUserEntity(form, user);
        userRepository.save(user);
        return 1;
    }

    public Users updateUsersInfoMobile(Long id, UserDTO form) {
        Users user = this.getUserById(id);
        userMapper.toUserEntity(form, user);
        userRepository.save(user);
        return user;
    }

    public int verifyIfEmailExists(String email) {
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent() ? 1 : 0;
    }

    public UserViewModel getSimplifiedUser(Users user){
        UserViewModel simplifiedUser = new UserViewModel();
        simplifiedUser.setIdUser(user.getId());
        simplifiedUser.setNickname(user.getNickname());
        simplifiedUser.setName(user.getName());
        simplifiedUser.setAbout(user.getAbout());
        return simplifiedUser;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[15];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
