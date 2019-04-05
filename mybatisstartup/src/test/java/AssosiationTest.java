import Entity.Employee;
import Mapper.EmployeeMapper;
import Utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class AssosiationTest {
    private Logger logger= Logger.getLogger(AssosiationTest.class);
    
    @Test
    public void testEmployee(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        EmployeeMapper emapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = emapper.getEmployee(1);
        logger.warn(employee);
        sqlSession.close();
    }
}
