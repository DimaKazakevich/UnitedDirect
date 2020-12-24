package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.dto.AuthenticationRequestDto;

import java.util.Map;

public interface AuthService {
    Map<Object, Object> authenticateUser(AuthenticationRequestDto requestDto);
}
