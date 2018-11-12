package io.github.lucasduete.pweb2.serviceauth.service;

import io.github.lucasduete.pweb2.serviceauth.dao.UserDaoInterface;
import io.github.lucasduete.pweb2.serviceauth.models.RoleEnum;
import io.github.lucasduete.pweb2.serviceauth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RequestScope
public class UserService {

    @Autowired
    private UserDaoInterface userDao;

    public void save(User user) {
        this.userDao.save(user);
    }

    public void delete(User user) {
        this.userDao.delete(user);
    }

    public List<User> listAll() {
        return this.userDao.findAll();
    }

    public List<User> listAllByRole(RoleEnum role) {
        return this.userDao.findAllByRole(role);
    }

    public User getByMatricula(String matricula) {
        return this.userDao.getOne(matricula);
    }

    public User getByMatricula(User user) {
        return this.userDao.getOne(user.getMatricula());
    }

}
