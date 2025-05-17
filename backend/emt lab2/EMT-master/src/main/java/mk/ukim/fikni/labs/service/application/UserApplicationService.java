package mk.ukim.fikni.labs.service.application;

import mk.ukim.fikni.labs.dto.CreateUserDto;
import mk.ukim.fikni.labs.dto.DisplayUserDto;
import mk.ukim.fikni.labs.dto.LoginResponseDto;
import mk.ukim.fikni.labs.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
