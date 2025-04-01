package com.exam.project.security;

import com.exam.project.dto.login.AuthResponseDto;
import com.exam.project.dto.login.LoginRequestDto;
import com.exam.project.dto.login.LoginResponseDto;
import com.exam.project.dto.register.RegisterRequestDto;
import com.exam.project.entity.Users;
import com.exam.project.exceptions.UserAlreadyExistsException;
import com.exam.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public Users registerNewUser(RegisterRequestDto registerRequestDto) {
        if(userRepository.existsByUserName(registerRequestDto.getUserName())){
            throw new UserAlreadyExistsException("Username '" + registerRequestDto.getUserName() + "' is already taken");
        }
        Users user = new Users();
        user.setUserName(registerRequestDto.getUserName());
        user.setFullName(registerRequestDto.getFullName());
        user.setRole(registerRequestDto.getRoles());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public LoginResponseDto authenticateUser(LoginRequestDto user) {
        try{
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

            if(authentication.isAuthenticated()) {

                UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(user.getUserName());

                String token = jwtService.generateToken(userDetails);
                Users userFetched = userRepository.findUserByUserName(user.getUserName());
                return LoginResponseDto.builder()
                        .status(200)
                        .data(new AuthResponseDto(token,userFetched))
                        .message("User authenticated Successfully")
                        .build();
            }
        }catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
        throw new AuthenticationServiceException("Authentication failed");
    }
}