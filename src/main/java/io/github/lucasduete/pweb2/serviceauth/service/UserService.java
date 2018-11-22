package io.github.lucasduete.pweb2.serviceauth.service;

import io.github.lucasduete.pweb2.serviceauth.dao.UserDaoInterface;
import io.github.lucasduete.pweb2.serviceauth.events.models.UserLogado;
import io.github.lucasduete.pweb2.serviceauth.events.models.UserTentativaFalhaLogin;
import io.github.lucasduete.pweb2.serviceauth.models.RoleEnum;
import io.github.lucasduete.pweb2.serviceauth.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDaoInterface userDao;

    private Logger log;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher =  applicationEventPublisher;
        this.log = LoggerFactory.getLogger(UserService.class);
    }

    public User save(User user) {
        return this.userDao.save(user);
    }

    public void delete(User user) {
        this.userDao.delete(user);
    }

    public void deleteById(Long id) {
        this.userDao.deleteById(id);
    }

    public List<User> listAll() {
        return this.userDao.findAll();
    }

    public List<User> listAllByRole(RoleEnum role) {
        return this.userDao.findAllByRole(role);
    }

    public User getById(Long id) {
        return this.userDao.getOne(id);
    }

    public User getByMatricula(String matricula) {
        return this.userDao.findByMatricula(matricula);
    }

    public User getByMatricula(User user) {
        return this.userDao.findByMatricula(user.getMatricula());
    }

    public User authenticate(String matricula, String password) {

        User userComMatricula = getByMatricula(matricula);

        if (userComMatricula != null) {
            User user = this.userDao.findByMatriculaEqualsAndPasswordEquals(matricula, password);

            if (user != null)
                applicationEventPublisher.publishEvent(user.usuarioLogado());
            else
                applicationEventPublisher.publishEvent(userComMatricula.tentativaFalhaLogin(password, matricula));

            return user;
        }
        else
            return null;
    }

    @EventListener
    public void usuarioLogado(UserLogado userLogado) {
        log.info(userLogado.toString());
    }

    @EventListener
    public void tentativaLogin(UserTentativaFalhaLogin userTentativa) {
        log.info(userTentativa.toString());
    }

}
