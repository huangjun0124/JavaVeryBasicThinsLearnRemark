package Service;

import Entity.Role;
import Mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper mapper = null;
    @Transactional(propagation = Propagation.REQUIRED, // 每次产生一个新的事物
    isolation = Isolation.READ_COMMITTED)
    public int insertRole(Role role) throws Exception {
        if(role.getRoleName().endsWith("4")){
            throw new Exception("ddd");
        }
        return mapper.insertRole(role);
    }
}
