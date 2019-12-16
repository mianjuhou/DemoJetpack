package com.potevio.app10.data.db;

import androidx.paging.DataSource;
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

    @Insert
    void insertUsers(List<User> users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("select * from t_user where id = :id")
    User findById(long id);

    @Query("select * from t_user order by id")
    DataSource.Factory<Integer, User> findAll();

}
