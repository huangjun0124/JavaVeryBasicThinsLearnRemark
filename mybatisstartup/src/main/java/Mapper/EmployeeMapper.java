package Mapper;

import Entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {
       public Employee getEmployee(long id);
}
