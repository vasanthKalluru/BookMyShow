package org.example.bookmyshow3;

import org.example.bookmyshow3.controllers.UserController;
import org.example.bookmyshow3.dtos.SignUpRequestDto;
import org.example.bookmyshow3.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShow3Application implements CommandLineRunner {
    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShow3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");
        SignUpResponseDto responseDto = userController.signUp(requestDto);

    }

}
