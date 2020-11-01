package by.kazakevich.uniteddirect.dto;

import by.kazakevich.uniteddirect.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationUserDto {

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Wrong email address")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 16, message = "The password must be equal or greater than 6 characters and less than 16 characters")
    private String password;

    public User ToUser(){

        return new User(this.getEmail(), this.getPassword());
    }
}
