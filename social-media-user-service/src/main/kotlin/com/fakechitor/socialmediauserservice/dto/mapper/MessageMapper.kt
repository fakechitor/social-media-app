package com.fakechitor.socialmediauserservice.dto.mapper

import com.fakechitor.socialmediauserservice.dto.response.MessageResponseDto
import com.fakechitor.socialmediauserservice.model.Message
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface MessageMapper {
    @Mapping(source = "sender.username", target = "sender")
    fun toResponseDto(message: Message): MessageResponseDto
}
