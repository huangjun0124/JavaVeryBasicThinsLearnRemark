public class UserServiceImpl {
    private String name;

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public void add() {
        System.out.println("This is add service");
    }
    public void delete(int id) {
        System.out.println("This is delete service：delete " + id );
    }
}
