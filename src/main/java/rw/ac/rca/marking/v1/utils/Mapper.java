package rw.ac.rca.marking.v1.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rw.ac.rca.marking.v1.models.User;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User getUserFromDTO(Object object, String password) {
        User user = getUserFromDTO(object);
        user.setPassword(passwordEncoder.encode(password));
        user.setId(null);
        return user;
    }

    public static User getUserFromDTO(Object object) {
        return modelMapper.map(object, User.class);
    }

}
