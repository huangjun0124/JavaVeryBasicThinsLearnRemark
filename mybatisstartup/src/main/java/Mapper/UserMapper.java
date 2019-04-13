package Mapper;

import Definitions.SexEnum;
import Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public int insertUser(User user);
    public User getUser( String id) ;
    public List<User> findUserByAnnotation(@Param("userName") String userName, @Param("sex")SexEnum sex);

    /**
     * RowBounds 实现分页    --->  运行时查看log可知，此种方式运行的SQL查询的数据还是全部，只是这里返回做了截断，此种方法并不是真的分页
     * @param userName
     * @param rowBounds
     * @return
     */
    public List<User> findUserByRowBounds(@Param("userName") String userName, RowBounds rowBounds);
}
