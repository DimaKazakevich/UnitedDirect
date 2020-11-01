package by.kazakevich.uniteddirect.validator;

import by.kazakevich.uniteddirect.dto.RegistrationUserDto;
import by.kazakevich.uniteddirect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationUserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationUserDto user = (RegistrationUserDto) o;

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already in use");
        }
    }
}
