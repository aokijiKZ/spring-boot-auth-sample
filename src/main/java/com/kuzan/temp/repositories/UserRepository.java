package com.kuzan.temp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kuzan.temp.models.UserModel;

@Repository
public class UserRepository {
    private List<UserModel> users = new ArrayList<>(
        List.of(
            new UserModel("1", "Alice", "alice@example.com"),
            new UserModel("2", "Bob", "bod@example.com")
        )
    );

    public void addUser(UserModel user) {
        users.add(user);
    }

    public List<UserModel> getUsers() {
        return users;
    }
    
    public void removeUser(UserModel user) {
        users.remove(user);
    }

    public void editUser(UserModel user){
        for(int i = 0;i < users.size(); i++){
            if(users.get(i).getId().equals(user.getId())){
                users.set(i, user);
                return;
            }
        }
    }
}
