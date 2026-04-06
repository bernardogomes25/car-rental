package com.aluguel.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

/**
 * Abstract base class for Usuario (User) entities in the car rental system
 */
@Serdeable
@Introspected
public abstract class Usuario {

    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String endereco;

    public Usuario() {
    }

    public Usuario(String login, String senha, String nome, String endereco) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Authenticate user
     * @param login user login
     * @param senha user password
     * @return true if authentication is successful
     */
    public boolean autenticar(String login, String senha) {
        return this.login != null && this.login.equals(login) &&
               this.senha != null && this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
