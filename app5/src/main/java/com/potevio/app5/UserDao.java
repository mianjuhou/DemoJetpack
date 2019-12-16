package com.potevio.app5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("select * from t_user")
    LiveData<List<User>> findAll();

    @Query("select * from t_user where id = :id")
    LiveData<User> findById(long id);
}
