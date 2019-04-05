package Service;


import Entity.Role;
import Mapper.RoleMapper;
import Utils.DateUtil;
import Utils.SqlSessionFactoryUtil;
import Utils.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.UUID;

public class RoleTest {
    public static void main(String[] args){
        Logger log = Logger.getLogger(RoleTest.class);
        SqlSession sqlSession = null;
        try{
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = new Role();
            role.setId(UUIDUtil.newUUIDStrWithoutDash());
            role.setRoleName("normal");
            role.setNote(DateUtil.getTimeStamp());
            //通过sqlSession操作数据库
            //sqlSession.insert("Mapper.RoleMapper.insertRole", role);
            // 通过 mapper 操作数据库, 更推荐这种方式
            roleMapper.insertRole(role);
            // 下面这个必须加上，否则不会提交更改到数据库
            sqlSession.commit();
            log.info(role);
        }  finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
