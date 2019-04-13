import Entity.Role;
import Mapper.RoleMapper;
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
}
