package com.unidac.tools.services;

import com.unidac.tools.dto.AuthenticationDTO;
import com.unidac.tools.dto.ResponseDTO;
import com.unidac.tools.dto.UserDTO;
import com.unidac.tools.dto.LoginResponseDTO;
import com.unidac.tools.entities.User;
import com.unidac.tools.exception.ApplicationException;
import com.unidac.tools.exception.CredentialException;
import com.unidac.tools.exception.ResourceAlreadyExistsException;
import com.unidac.tools.repositories.UserRepository;
import com.unidac.tools.utils.HeaderUtils;
import com.unidac.tools.utils.MessageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthorizationService(@Lazy AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public ResponseEntity<LoginResponseDTO> checkLogin(AuthenticationDTO authenticationDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(),authenticationDTO.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            SecurityContextHolder.getContext().setAuthentication(auth);

            var user = (User) auth.getPrincipal();
            var jwtCookie = jwtUtils.generateJwtCookie(user);

            HttpHeaders httpHeaders = HeaderUtils.getHeaders(jwtCookie);

            return new ResponseEntity<>(new LoginResponseDTO(user.getId()), httpHeaders, HttpStatus.OK);
        }catch (BadCredentialsException e){
            throw new CredentialException(e.getMessage());
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }

    public ResponseEntity<Void> logout() {
        var jwtCookie = jwtUtils.getCleanJwtCookie();
        var httpHeaders = HeaderUtils.getHeaders(jwtCookie);
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }

}
