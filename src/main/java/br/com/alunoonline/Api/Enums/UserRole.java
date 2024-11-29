package br.com.alunoonline.Api.Enums;

public enum UserRole {

    PROFESSOR("ROLE_PROFESSOR"),
    ALUNO("ROLE_ALUNO");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
