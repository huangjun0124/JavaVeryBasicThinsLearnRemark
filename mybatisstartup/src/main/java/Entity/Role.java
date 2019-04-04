package Entity;

public class Role {

    private String id;
    private String roleName;
    private String note;

    public String getId(){return id;}
    public void setId(String id){this.id = id;}


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

  
    public String toString(){
        return  "RoleBean{" +
                "id='" + id + '\'' +
                ",roleName='" + roleName + '\'' +
                ",note='" + note + '\'' +
                "}";
    }
}
