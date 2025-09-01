package br.com.controly.services;

import br.com.controly.dtos.CreateNewUserDTO;
import br.com.controly.viewmodels.SimplifiedUserViewModel;
import br.com.controly.dtos.UpdateUsersInfoDTO;
import br.com.controly.exception.EmailAlreadyExistsException;
import br.com.controly.exception.UsersIdNotFould;
import br.com.controly.mappers.UserMapper;
import br.com.controly.jpa.UserRepository;
import br.com.controly.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public UserEntity createNewUser(CreateNewUserDTO newUser) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) throw new EmailAlreadyExistsException();
        UserEntity user = newUser.convert();
        user.setToken(generateNewToken());
        return userRepository.save(user);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UsersIdNotFould::new);
    }

    public List<UserEntity> getListOfUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public int deleteUserById(Long id) {
        UserEntity user = this.getUserById(id);
        // if user is already disabled then return 0
        // otherwise, disable this user and return 1
        return user.disableUser();
    }


    public int updateUsersInfo(Long id, UpdateUsersInfoDTO form) {
        UserEntity user = this.getUserById(id);
        userMapper.updateUserFromDto(form, user);
        userRepository.save(user);
        return 1;
    }

    public UserEntity updateUsersInfoMobile(Long id, UpdateUsersInfoDTO form) {
        UserEntity user = this.getUserById(id);
        userMapper.updateUserFromDto(form, user);
        userRepository.save(user);
        return user;
    }

    public int verifyIfEmailExists(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent() ? 1 : 0;
    }

    public SimplifiedUserViewModel getSimplifiedUser(UserEntity user){
        SimplifiedUserViewModel simplifiedUser = new SimplifiedUserViewModel();
        simplifiedUser.setIdUser(user.getIdUser());
        simplifiedUser.setNickname(user.getNickname());
        simplifiedUser.setName(user.getName());
        simplifiedUser.setAbout(user.getAbout());
        simplifiedUser.setAvatar(user.getAvatar());
        simplifiedUser.setAvatarPng(user.getAvatarPng());
//        userMapper.getDtoFromUser(user, simplifiedUser);
        return simplifiedUser;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[15];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
