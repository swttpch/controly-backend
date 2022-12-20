package controly.service;

import controly.dto.UpdateUsersInfoRequest;
import controly.entity.UserEntity;
import controly.dto.CreateNewUserRequest;
import controly.exception.EmailAlreadyExistsException;
import controly.exception.UsersIdNotFould;
import controly.mapper.UserMapper;
import controly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserEntity createNewUser(CreateNewUserRequest newUser) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) throw new EmailAlreadyExistsException();
        return userRepository.save(newUser.convert());
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


    public int updateUsersInfo(Long id, UpdateUsersInfoRequest form) {
        UserEntity user = this.getUserById(id);
        userMapper.updateUserFromDto(form, user);
        userRepository.save(user);
        return 1;
    }

    public UserEntity login(String email, String senha) {
        Optional<UserEntity> userOptional = userRepository.findByEmailAndPassword(email, senha);
        return userOptional.orElse(null);
    }


    public int verifyIfEmailExists(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent() ? 1 : 0;
    }
}
