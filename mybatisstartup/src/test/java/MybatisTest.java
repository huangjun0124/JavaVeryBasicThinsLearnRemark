import Entity.Role;
import Mapper.RoleMapper;
import Utils.DateUtil;
import Utils.SqlSessionFactoryUtil;
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
}
