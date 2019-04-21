import Entity.Role;
import Service.RoleListService;
import Utils.DateUtil;
import Utils.SysPrint;
import Utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TransactionTest {
    @Test
    public void doTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        RoleListService service = ctx.getBean(RoleListService.class);
        List<Role> list = new ArrayList<Role>();
        for (int i = 1; i < 5; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setNote(DateUtil.getTimeStamp());
            role.setId(UUIDUtil.newUUIDStrWithoutDash());
            list.add(role);
        }
        int count = service.insertRoleList(list);
        SysPrint.PrintLine("inserted " + count);
    }
}
