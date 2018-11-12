package io.github.lucasduete.pweb2.serviceauth.dao;

import io.github.lucasduete.pweb2.serviceauth.models.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao {

    @Autowired
    private UserDaoInterface userDao;

    public void save(User user) {
        this.userDao.save(user);
    }

    public User getByMatricula(String matricula) {
        return this.userDao.getOne(matricula);
    }

    public User getByMatricula(User user) {
        return this.userDao.getOne(user.getMatricula());
    }

}
