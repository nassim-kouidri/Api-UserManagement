package com.nassim.usermanagement.DTO.Mapper;

import com.nassim.usermanagement.DTO.Request.UserRequest;
import com.nassim.usermanagement.DTO.Response.UserResponse;
import com.nassim.usermanagement.Model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserModel fromRequestToEntity(UserRequest userRequest);
    UserResponse fromEntityToResponse(UserModel userModel);
}
