package vn.hoidanit.springsieutoc.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.springsieutoc.domain.dto.RegisterDTO;
import vn.hoidanit.springsieutoc.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecker, RegisterDTO> {

    private final UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // check is password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("Password must match")
                    .addPropertyNode("password")
                    .addConstraintViolation();

            context.buildConstraintViolationWithTemplate("Password must match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            valid = false;
        }

        // additional validations can be added here

        // check email
        if (this.userService.checkEmailExist(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email already exists").addPropertyNode("email")
                    .addConstraintViolation().disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
