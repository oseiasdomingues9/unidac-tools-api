package com.unidac.tools.services;

import com.unidac.tools.dto.ResponseDTO;
import com.unidac.tools.dto.UserDTO;
import com.unidac.tools.entities.User;
import com.unidac.tools.exception.ResourceAlreadyExistsException;
import com.unidac.tools.exception.ResourceNotFoundException;
import com.unidac.tools.mapper.UserMapper;
import com.unidac.tools.repositories.UserRepository;
import com.unidac.tools.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    protected UserMapper userMapper;

    public ResponseEntity<ResponseDTO> create(UserDTO userDTO) {

        if(userRepository.findByLogin(userDTO.login()) != null) throw new ResourceAlreadyExistsException("Usuário já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        User user = new User(userDTO.login(),encryptedPassword, userDTO.role());
        userRepository.save(user);
        return new ResponseEntity<>(MessageUtils.successMessage("Usuário criado com sucesso",user), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> findById(String id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return new ResponseEntity<>(MessageUtils.successMessage("Usuário encontrado com sucesso",userMapper.toUserDTO(userOptional.get())),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado");
        }
    }

    public ResponseEntity<ResponseDTO> findAll(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userMapper.toUserListDTO(userList);
        if (!userDTOList.isEmpty()){
            return new ResponseEntity<>(MessageUtils.successMessage("Os usuários foram encontrados com sucesso",userDTOList),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Nenhum usuário foi encontrado");
        }
    }

    public ResponseEntity<ResponseDTO> update(UserDTO userDTO, String id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user = opt.get();
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
            user.setLogin(userDTO.login());
            user.setPassword(encryptedPassword);
            user.setRole(userDTO.role());
            userRepository.save(user);
            return new ResponseEntity<>(MessageUtils.successMessage("Usuário Atualizado com sucesso",user),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Nenhum usuário foi encontrado");
        }
    }

    public ResponseEntity<ResponseDTO> delete(String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(MessageUtils.successMessage("Usuário deletado com sucesso",null),HttpStatus.OK);
    }






}
