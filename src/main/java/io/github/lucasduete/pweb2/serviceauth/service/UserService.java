package io.github.lucasduete.pweb2.serviceauth.service;

import io.github.lucasduete.pweb2.serviceauth.dao.UserDaoInterface;
import io.github.lucasduete.pweb2.serviceauth.models.RoleEnum;
import io.github.lucasduete.pweb2.serviceauth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDaoInterface userDao;

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

        if (getByMatricula(matricula) != null)
            return this.userDao.findByMatriculaEqualsAndPasswordEquals(matricula, password);
        else
            return null;

    }

}
