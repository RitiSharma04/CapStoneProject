package com.natwest.service.implementation;

import com.natwest.dto.AccountDto;
import com.natwest.dto.UserDto;
import com.natwest.exception.userregistrationexceptions.*;
import com.natwest.model.Account;
import com.natwest.model.User;
import com.natwest.repository.UserRepository;
import com.natwest.service.UserService;
import com.natwest.utility.UserAppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistration implements UserService {
    @Autowired
    UserRepository userRepository;


    public User RegisterUser(User user) throws InvalidEmailFoundException, InvalidMobileNumberFoundException, NameEmptyFoundException, PasswordEmptyFoundException {
        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistexception(UserAppConstants.USER_ALREADY_EXIST);
        }
        if (user.getEmailId() == null || !user.getEmailId().endsWith("@gmail.com")) {
            throw new InvalidEmailFoundException(UserAppConstants.INVALID_EMAIL_FOUND_MESSAGE);
        }
        if (!(user.getMobileNumber().length()==10)){
            throw new InvalidMobileNumberFoundException(UserAppConstants.INVALID_MOBILE_NUMBER_FOUND_MESSAGE);
        }
        if(user.getName().isEmpty()){
            throw new NameEmptyFoundException(UserAppConstants.INVALID_NAME_FOUND_MESSAGE);
        }
        if (user.getPassword().isEmpty()){
            throw new PasswordEmptyFoundException(UserAppConstants.INVALID_PASSWORD_FOUND_MESSAGE);
        }
        return userRepository.save(user);

    }

    @Override
    public UserDto getUserById(int id) {
        User user=userRepository.findById(id).get();
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user,UserDto.class);
        return userDto;
    }

}
