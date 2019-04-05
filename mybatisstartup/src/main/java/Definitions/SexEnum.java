package Definitions;

public enum SexEnum {
    FEMALE(66, "女"),
    MALE(99,"男");

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SexEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static SexEnum getSexById(int id){
        for (SexEnum sex : SexEnum.values()){
            if (sex.getId() == id) {
                return sex;
            }
        }
        return null;
    }

    public static String getName(int id){
        for (SexEnum sex : SexEnum.values()){
            if (sex.getId() == id) {
                return sex.name;
            }
        }
        return null;
    }

    public static int getId(String name){
        for (SexEnum sex : SexEnum.values()){
            if (sex.getName().equals(name)) {

                return sex.getId();
            }
        }
        return -1;
    }
}
