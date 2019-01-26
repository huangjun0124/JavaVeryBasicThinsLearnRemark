package Model;

public class Book {
    private String id;
    private String name;//书名
    private double price;//价格
    private int pnum;//数量
    private String category;//分类
    private String description;//描述
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getPnum() {
        return pnum;
    }
    public void setPnum(int pnum) {
        this.pnum = pnum;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", price=" + price + ", pnum=" + pnum + ", category=" + category
                + ", description=" + description + "]";
    }
}

/*
建表语句：
create table books(
id varchar(200) primary key,
name varchar(100) not null,
price double,
pnum int,
category varchar(50),
description varchar(200)
);

INSERT INTO books VALUES('1001','java编程思想',98,100,'计算机','好书');
INSERT INTO books VALUES('1002','风雨也无情',10,20,'言情','好书');
INSERT INTO books VALUES('1003','天涯何需老',20,30,'武侠','好书');

SELECT * FROM books;

 */
