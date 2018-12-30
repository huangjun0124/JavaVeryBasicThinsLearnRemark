package DataStructureSamples;

public enum EnumWeekDay {
    MONDAY("星期一", 2),
    TUESDAY("星期二", 5),
    WEDNESDAY("星期三",9),
    THURSDAY("星期四",12),
    FRIDAY("星期五", 17),
    SATURDAY("星期六",19),
    SUNDAY("星期日",22);//记住要用分号结束

    private String desc;//中文描述
    private int index;
    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private EnumWeekDay(String desc, int index){
        this.desc=desc;
        this.index = index;
    }

    /**
     * 覆盖
     * @return
     */
    @Override
    public String toString() {
        return desc;
    }

    public int getIndex(){
        return index;
    }
}
