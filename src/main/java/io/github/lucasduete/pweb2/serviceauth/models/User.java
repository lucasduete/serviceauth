package io.github.lucasduete.pweb2.serviceauth.models;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String matricula;

    @Enumerated
    private RoleEnum role;

    private String password;

    public User() {

    }

    public User(String matricula, RoleEnum role, String password) {
        this.matricula = matricula;
        this.role = role;
        this.password = password;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!matricula.equals(user.matricula)) return false;
        if (role != user.role) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = matricula.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("matricula='").append(matricula).append('\'');
        sb.append(", role=").append(role);
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
