package com.growworld.dto;
import jakarta.validation.constraints.*; import java.util.UUID;
public final class AuthDtos { private AuthDtos(){} public record RegisterRequest(@NotBlank @Size(max=40) String displayName,@NotBlank @Email @Size(max=254) String email,@NotBlank @Size(min=8,max=72) String password){} public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){} public record UserResponse(UUID id,String displayName,String email){} }
