package by.kazakevich.uniteddirect.services.impl;

import by.kazakevich.uniteddirect.domain.Role;
import by.kazakevich.uniteddirect.domain.User;
import by.kazakevich.uniteddirect.dto.AuthenticationRequestDto;
import by.kazakevich.uniteddirect.repository.UserRepository;
import by.kazakevich.uniteddirect.security.jwt.JwtTokenProvider;
import by.kazakevich.uniteddirect.security.jwt.JwtUser;
import by.kazakevich.uniteddirect.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public Map<Object, Object> authenticateUser(AuthenticationRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                requestDto.getEmail(), requestDto.getPassword()));

        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        String token = jwtTokenProvider.createToken(userDetails.getEmail(), loginUser(requestDto));

        Map<Object, Object> response = new HashMap<>();
        response.put("email", userDetails.getEmail());
        response.put("token", token);
        response.put("roles", roles);

        return response;
    }

    private List<Role> loginUser(AuthenticationRequestDto request) {
        String email = request.getEmail();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        return user.getRoles();
    }
}
