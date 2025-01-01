package org.example.bookmyshow3.controllers;

import org.example.bookmyshow3.dtos.ResponseStatus;
import org.example.bookmyshow3.dtos.SignUpResponseDto;
import org.example.bookmyshow3.dtos.SignUpRequestDto;
import org.example.bookmyshow3.services.UserService;
import org.example.bookmyshow3.models.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        User user;

        try {
            user = userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
            signUpResponseDto.setUserId(user.getId());
            signUpResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            signUpResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return signUpResponseDto;
    }
}
