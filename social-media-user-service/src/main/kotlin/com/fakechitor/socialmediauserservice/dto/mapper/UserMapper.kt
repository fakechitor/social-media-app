package com.fakechitor.socialmediauserservice.dto.mapper

import com.fakechitor.socialmediauserservice.dto.request.UserLoginDto
import com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto
import com.fakechitor.socialmediauserservice.dto.response.UserResponseDto
import com.fakechitor.socialmediauserservice.model.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    @Mapping(source = "login", target = "username")
    fun loginDtoToModel(userLoginDto: UserLoginDto): User

    @Mapping(source = "login", target = "username")
    fun registerDtoToModel(userRegisterDto: UserRegisterDto): User

    @Mapping(target = "username", source = "username")
    fun modelToDto(user: User?): UserResponseDto
}
