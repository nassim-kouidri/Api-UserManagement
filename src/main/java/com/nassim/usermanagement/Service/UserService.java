package com.nassim.usermanagement.Service;

import com.nassim.usermanagement.DTO.Mapper.UserMapper;
import com.nassim.usermanagement.DTO.Request.UserRequest;
import com.nassim.usermanagement.DTO.Response.UserResponse;
import com.nassim.usermanagement.Model.UserModel;
import com.nassim.usermanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> findByAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponse saveUser(UserRequest userRequest) {
        UserModel userModel = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userModel = userRepository.save(userModel); // Mettre à jour userModel avec l'ID généré
        return UserMapper.MAPPER.fromEntityToResponse(userModel);
    }


    public UserResponse updateUser(UserRequest userRequest, Long id) {  //Modifie tous les champs
        Optional<UserModel> checkExistingUser = findById(id);

        if (! checkExistingUser.isPresent())
            throw new RuntimeException("Employee Id "+ id + " Not Found!");

        UserModel userModel = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userModel.setId(id);
        userRepository.save(userModel);
        return UserMapper.MAPPER.fromEntityToResponse(userModel);
    }

    public UserResponse updateUserWithDTO(UserRequest userRequest, Long id) {   //La meme qu'en haut mais permet de ne pas modifier certains champs
        Optional<UserModel> checkExistingUser = findById(id);
        if (!checkExistingUser.isPresent()) {
            throw new RuntimeException("Employee Id " + id + " Not Found!");
        }

        UserModel userModel = checkExistingUser.get();
        String address = userModel.getAddress(); // Récupération de l'adresse actuelle

        userModel.setName(userRequest.getName());
        userModel.setGender(userRequest.getGender());
        userModel.setDateOfBirth(userRequest.getDateOfBirth());
        userModel.setAddress(address); // Réassignation de l'adresse actuelle

        userRepository.save(userModel);
        return UserMapper.MAPPER.fromEntityToResponse(userModel);
    }

    public UserModel updateUserWithoutDTO(UserModel updatedUserModel, Long id) {  //la meme qu'en haut mais sans passer par le package DTO
        Optional<UserModel> existingUserModel = findById(id);
        if (!existingUserModel.isPresent()) {
            throw new RuntimeException("User Id " + id + " Not Found!");
        }

        UserModel userModel = existingUserModel.get();

        userModel.setName(updatedUserModel.getName());
        userModel.setGender(updatedUserModel.getGender());
        userModel.setDateOfBirth(updatedUserModel.getDateOfBirth());

        userRepository.save(userModel);
        return userModel;
    }

    public String HelloWord(){
        return  "Hello World";
    }

}
