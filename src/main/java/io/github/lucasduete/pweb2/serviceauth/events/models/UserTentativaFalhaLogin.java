package io.github.lucasduete.pweb2.serviceauth.events.models;

import java.time.LocalDateTime;

public class UserTentativaFalhaLogin {

    private String password;
    private String matricula;
    private LocalDateTime horaTentativaLogin;

    {
        horaTentativaLogin = LocalDateTime.now();
    }

    public UserTentativaFalhaLogin() {

    }

    public UserTentativaFalhaLogin(String password, String matricula) {
        this.password = password;
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getHoraTentativaLogin() {
        return horaTentativaLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTentativaFalhaLogin that = (UserTentativaFalhaLogin) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (matricula != null ? !matricula.equals(that.matricula) : that.matricula != null) return false;
        return horaTentativaLogin != null ? horaTentativaLogin.equals(that.horaTentativaLogin) : that.horaTentativaLogin == null;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (matricula != null ? matricula.hashCode() : 0);
        result = 31 * result + (horaTentativaLogin != null ? horaTentativaLogin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTentativaFalhaLogin{");
        sb.append("password='").append(password).append('\'');
        sb.append(", matricula='").append(matricula).append('\'');
        sb.append(", horaTentativaLogin=").append(horaTentativaLogin);
        sb.append('}');
        return sb.toString();
    }
}
