import Definitions.AccountTypeEnum;
import Definitions.SexEnum;
import Entity.Role;
import Entity.User;
import Mapper.RoleMapper;
import Mapper.UserMapper;
import Utils.DateUtil;
import Utils.SqlSessionFactoryUtil;
import Utils.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MybatisTest {

    private Logger logger;
    public MybatisTest(){
        logger = Logger.getLogger(MybatisTest.class);
    }

    @Test
    public  void findRoleById(){
        //通过工厂的到sqlSession
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        //通过sqlSession操作数据库
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.getRole("bec7230480c4480bb4c85a79b3f741cd");
        logger.info(role);
        sqlSession.close();
    }

    @Test
    public  void findRolesByName(){
        //通过工厂的到sqlSession
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        //通过sqlSession操作数据库
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roles = mapper.findRoles("ma");
        for (Role role: roles) {
            logger.info(role);
        }
        sqlSession.close();
    }

    @Test
    public  void deleteRoleById(){
        //通过工厂的到sqlSession
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        //通过sqlSession操作数据库
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        int iRet = mapper.deleteRole("aaecec007a9e4e079641ba3133de1f4d");
        logger.info("delete role [aaecec007a9e4e079641ba3133de1f4d] result : " + iRet);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public  void updateRole(){
        //通过工厂的到sqlSession
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();

        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.getRole("bec7230480c4480bb4c85a79b3f741cd");
        role.setNote(DateUtil.getTimeStamp());
        role.setRoleName("testupdate");
        mapper.updateRole(role);

        Role role1 = new Role();
        role1.setId("cda3e3a9c4aa415da505920ffc3afe2a");
        role1.setRoleName("testupdatenew");
        role1.setNote(DateUtil.getTimeStamp());
        mapper.updateRole(role1);
        
        sqlSession.commit();
        sqlSession.close();

        logger.info("Roles update done.");
    }

    @Test
    public void userMapperAdd(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(UUIDUtil.newUUIDStrWithoutDash());
        user.setUserName("keith");
        user.setSex(SexEnum.MALE);
        user.setAccountType(AccountTypeEnum.Admin);
        mapper.insertUser(user);

        user = new User();
        user.setId(UUIDUtil.newUUIDStrWithoutDash());
        user.setUserName("jun");
        user.setSex(SexEnum.FEMALE);
        user.setAccountType(AccountTypeEnum.FreeUser);
        mapper.insertUser(user);
        sqlSession.commit();
        
        sqlSession.close();
    }

    @Test
    public void userMapperSelect(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUser("a6d9ec6ce4f9428f8dd7de41f54afa06");
        logger.info(user);
        user = mapper.getUser("b70ad327340640b890cd5a67426de48a");
        logger.info(user);
        sqlSession.close();
    }
}
