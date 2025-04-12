package com.fakechitor.socialmediaauthorization.docs.jwt

import com.fakechitor.socialmediaauthorization.dto.response.ExceptionMessageDto
import com.fakechitor.socialmediaauthorization.dto.response.TokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    tags = ["Jwt"],
    summary = "Refresh token",
    description = "Get new access token with refresh token",
    responses = [
        ApiResponse(
            responseCode = "200",
            description = "Token refreshed successfully",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = TokenResponse::class),
                    examples = [
                        ExampleObject(
                            value = """
                        { 
                            "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWFyaSIsImlhdCI6MTc0NDQ1MDY0MCwiZXhwIjoxNzQ0NDU0MjQwfQ.9coVulKOjCvhbgEKcD7z7nWhThgbMF5LYusOFyhB1DA"
                        }
                    """,
                        ),
                    ],
                ),
            ],
        ),
        ApiResponse(
            responseCode = "401",
            description = "Token is invalid",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ExceptionMessageDto::class),
                    examples = [
                        ExampleObject(
                            value = """
                            {
                                "message": "Invalid refresh token."
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
annotation class RefreshTokenDocs
