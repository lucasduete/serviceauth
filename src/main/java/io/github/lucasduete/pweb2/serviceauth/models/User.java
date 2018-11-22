package io.github.lucasduete.pweb2.serviceauth.models;

import io.github.lucasduete.pweb2.serviceauth.events.models.UserLogado;
import io.github.lucasduete.pweb2.serviceauth.events.models.UserTentativaFalhaLogin;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity(name = "usuario")
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricula;

    @Enumerated
    @Column(nullable = false)
    private RoleEnum role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {

    }

    public User(String matricula, RoleEnum role, String password, String email) {
        this.matricula = matricula;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserLogado usuarioLogado() {
        return registerEvent(new UserLogado(getEmail(), getPassword(), getMatricula()));
    }

    public UserTentativaFalhaLogin tentativaFalhaLogin() {
        return registerEvent(new UserTentativaFalhaLogin(getPassword(), getMatricula()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!matricula.equals(user.matricula)) return false;
        if (role != user.role) return false;
        if (!password.equals(user.password)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + matricula.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", matricula='").append(matricula).append('\'');
        sb.append(", role=").append(role);
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
