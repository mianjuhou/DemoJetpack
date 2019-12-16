package com.potevio.app02;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class UserViewModel extends ViewModel {

    private PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(30)
//            .setPrefetchDistance(10)
//            .setEnablePlaceholders(false)
            .build();

    private final UserDao userDao;

    public UserViewModel() {
        userDao = AppDataBase.getInstance(App.context).userDao();
    }

    public LiveData<PagedList<User>> getUsers() {
        return new LivePagedListBuilder(userDao.findAll(), config)
                .setBoundaryCallback(new PagedList.BoundaryCallback<User>() {
                    @Override
                    public void onItemAtEndLoaded(@NonNull User user) {
                        System.out.println("UserViewModel.onItemAtEndLoaded:" + user.toString());
                    }
                })
                .build();
    }

    public void addUser(String name, int age) {
        User user = new User(name, age);
        userDao.insertAll(user);
    }

    public void deleteUser(long id) {
        User user = new User(id);
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
