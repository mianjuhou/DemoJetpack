package com.potevio.app10.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.potevio.app10.App;
import com.potevio.app10.data.db.AppDataBase;
import com.potevio.app10.data.db.User;
import com.potevio.app10.data.db.UserDao;
import com.potevio.app10.data.remote.UserService;
import com.potevio.app10.http.ResultObserver;
import com.potevio.app10.http.Services;
import com.potevio.app10.http.TransformUtil;
import java.util.List;

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

    public LiveData getUsers() {
        return new LivePagedListBuilder(userDao.findAll(), config)
                .setBoundaryCallback(new PagedList.BoundaryCallback<User>() {
                    @Override
                    public void onItemAtEndLoaded(@NonNull User user) {
                        Services.createService(UserService.class)
                                .getUsers(user)
                                .compose(TransformUtil.defaultSchedulers())
                                .subscribe(new ResultObserver<List<User>>() {
                                    @Override
                                    public void onSucceed(List<User> datas) {
                                        userDao.insertUsers(datas);
                                    }

                                    @Override
                                    public void _onError(String errorMsg) {
                                        System.out.println(errorMsg);
                                    }
                                });
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
