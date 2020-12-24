package by.kazakevich.uniteddirect.controller;

import by.kazakevich.uniteddirect.domain.User;
import by.kazakevich.uniteddirect.dto.AuthenticationRequestDto;
import by.kazakevich.uniteddirect.dto.RegistrationUserDto;
import by.kazakevich.uniteddirect.exception.ValidationException;
import by.kazakevich.uniteddirect.services.AuthService;
import by.kazakevich.uniteddirect.services.UserService;
import by.kazakevich.uniteddirect.validator.UserValidator;
import com.jcabi.aspects.Loggable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final AuthService authService;

    public AuthenticationController(UserService userService, UserValidator userValidator, AuthService authService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.authService = authService;
    }

    @PostMapping("login")
    @Loggable
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            return ResponseEntity.ok(authService.authenticateUser(requestDto));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    @Loggable
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationUserDto userDetails, BindingResult errors) {

        userValidator.validate(userDetails, errors);

        if(errors.hasErrors()){
            throw new ValidationException(errors);
        }

        User user = userDetails.ToUser();
        userService.register(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
