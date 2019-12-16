package com.potevio.app8;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {
    private final UserDao userDao;

    public MyViewModel() {
        userDao = AppDataBase.getInstance(App.context).userDao();
    }

    public LiveData<List<User>> getUsers() {
        return userDao.findAll();
    }

    public void addUser(String name, int age) {
        User user = new User(name, age);
        userDao.insertAll(user);
    }

    public void deleteUser(long id) {
        User user = new User();
        user.setId(id);
        userDao.delete(user);
    }

    public void updateUser(long id, String name, String age) {
        User user = userDao.findById(id);
        if (user == null) {
            return;
        }
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }
        if (age != null && !age.isEmpty()) {
            int ageInt = Integer.parseInt(age);
            user.setAge(ageInt);
        }
        userDao.update(user);
    }
}
