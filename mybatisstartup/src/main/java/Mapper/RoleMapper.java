package Mapper;

        import Definitions.PageParams;
        import Entity.Role;
        import org.apache.ibatis.annotations.Param;

        import java.util.List;
        import java.util.Map;

public interface RoleMapper {
    public int insertRole(Role role);
    public int deleteRole(String id) ;
    public int updateRole(Role role) ;
    public Role getRole( String id) ;
    public List<Role> findRoles(String roleName);
    public Map findReturnMap(String roleName);
    public List<Role> findRolesPaged(@Param("pageParams") PageParams pageParams, @Param("roleName") String roleName);
}
