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
    @Transactional(propagation = Propagation.REQUIRES_NEW, // 每次产生一个新的事物
    isolation = Isolation.READ_COMMITTED)
    public int insertRole(Role role) {
        return mapper.insertRole(role);
    }
}
