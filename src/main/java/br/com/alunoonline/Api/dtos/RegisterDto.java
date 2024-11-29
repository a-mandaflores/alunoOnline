package br.com.alunoonline.Api.dtos;

import br.com.alunoonline.Api.Enums.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
