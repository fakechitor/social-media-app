package com.fakechitor.socialmediaauthorization.docs.auth

import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.dto.response.ExceptionMessageDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Authentication"],
    summary = "Authenticate user",
    description = "Authenticate user and return access and refresh token",
    responses = [
        ApiResponse(
            responseCode = "200",
            description = "User authenticated successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AuthenticationResponse::class),
                    examples = [
                        ExampleObject(
                            value = """
                        {
                            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWFyaSIsImlhdCI6MTc0NDEwMTgyNSwiZXhwIjoxNzQ0MTA1NDI1fQ.QItVUSSDDfn4LAspbpfNPkCVkdsWMcVm6IzD_R5l8N4",
                            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWFyaSIsImlhdCI6MTc0NDEwMTgyNSwiZXhwIjoxNzQ0MTg4MjI1fQ.0ZPs7LkmXbIksolUFngx7TAyzMGdCiYDLaRsC0_HrZE"
                        }
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "404",
            description = "User with that credentials does not exist",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "User not found"
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
                    schema = Schema(implementation = ExceptionMessageDto::class),
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
annotation class AuthenticateUser
