package Mapper;

import Entity.User;

public interface UserMapper {
    public int insertUser(User user);
    public User getUser( String id) ;
}
