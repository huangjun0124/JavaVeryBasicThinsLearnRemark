import Entity.Employee;
import Entity.Role;
import Entity.User;
import Mapper.EmployeeMapper;
import Mapper.RoleMapper;
import Mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MybatisSpringTest {
    private Logger logger;
    public MybatisSpringTest(){
        logger = Logger.getLogger(MybatisTest.class);
    }

    @Test
    public void TestRoleMapper(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        RoleMapper rp = ctx.getBean(RoleMapper.class);
        List<Role> roles = rp.findRoles("test");
        logger.warn("Mybatis Spring test ...............");
        for(Role r : roles){
            logger.info(r);
        }
    }

    @Test
    public void TestMapperScanner(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        UserMapper up = ctx.getBean(UserMapper.class);
        RowBounds rb = new RowBounds(2, 2);
        List<User> users = up.findUserByRowBounds("ke", rb);
        logger.warn("Mybatis Spring MapperScanner test ...............");
        for(User u : users){
            logger.info(u);
        }

        EmployeeMapper emapper = ctx.getBean(EmployeeMapper.class);
        Employee employee = emapper.getEmployee(1);
        logger.warn(employee);
    }
}
