package com.fakechitor.socialmediauserservice.docs.internal

import com.fakechitor.socialmediauserservice.dto.response.ErrorResponseDto
import com.fakechitor.socialmediauserservice.dto.response.UserResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Internal"],
    summary = "Authorize user",
    description = "Authorize user and retrieve user information internally. Requires \"X-Internal-Secret\" header with a secret code",
    responses = [
        ApiResponse(
            responseCode = "200",
            description = "User information retrieved successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "id" : "1",
                            "username": "timagucci",
                            "email": "guccigangtima@gmail.com",
                            "password": "iamrichtima"
                        }
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "Failed to login with username: venom"
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorResponseDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "Internal Server Error"
                            }
                        """,
                        ),
                    ],
                ),
            ],
        ),
    ],
)
annotation class GetUserByUsernameDocs
