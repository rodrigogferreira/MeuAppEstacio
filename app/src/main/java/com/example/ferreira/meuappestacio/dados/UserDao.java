package com.example.ferreira.meuappestacio.dados;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diogosoares.estacioapp.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT *  FROM user WHERE username LIKE :username AND encrypted_password LIKE :password LIMIT 1")
    User getAuthenticatedUser(String username, String password);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
