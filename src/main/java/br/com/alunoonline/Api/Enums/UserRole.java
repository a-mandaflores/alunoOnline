package br.com.alunoonline.Api.Enums;

public enum UserRole {

    PROFESSOR("professor"),
    ALUNO("aluno"),
    ;

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
