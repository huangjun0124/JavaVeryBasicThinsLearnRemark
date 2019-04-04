package Mapper;

        import Entity.Role;

        import java.util.List;

public interface RoleMapper {
    public int insertRole(Role role);
    public int deleteRole(String id) ;
    public int updateRole(Role role) ;
    public Role getRole( String id) ;
    public List<Role> findRoles(String roleName);
}
