package DAL;

import Model.Book;
import Model.Model.PageResult;
import Utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BookDAL {
    public List<Book> findAllBooks() {
        // 使用数据库连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        try {
            return qr.query("select * from books", new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addBook(Book book) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //注意：这时的book由Servlet传入时，是没有ID,手动设置一个UUID
        try {
            qr.update("insert into books values(?,?,?,?,?,?)",
                    UUID.randomUUID().toString(), book.getName(), book.getPrice(),
                    book.getPnum(), book.getCategory(), book.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBookById(String id) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        try {
            return qr.query("select * from books where id = ?", new BeanHandler<Book>(Book.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void uppdateBook(Book book) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update books set name = ?,price = ?,pnum = ?,category = ?,description = ? where id = ?";
        try {
            qr.update(sql, book.getName(), book.getPrice(),
                    book.getPnum(), book.getCategory(), book.getDescription(), book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookById(String bookId) {
        // TODO Auto-generated method stub
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from books where id = ?";
        try {
            qr.update(sql, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllBook(String[] ids) {
        // TODO Auto-generated method stub
        //第一种方式
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from books where id = ?";
		/*for(String bookId : ids){
			qr.update(sql,bookId);
		}*/

        //第二种方式:
		/*for(String bookId : ids){
			deleteBookById(bookId);
		}*/

        //第三种-二维数组，用来匹配多个参数的情况
        Object[][] params = new Object[ids.length][];
        for (int i = 0; i < ids.length; i++) {
            params[i] = new Object[]{ids[i]};
        }
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findBookByCondition(String id, String name, String category, String minprice, String maxprice) {
        // TODO Auto-generated method stub
        String sql = "select * from books where 1=1";
        if (!"".equals(id)) {//添加id条件
            sql += " and id = '" + id + "'";
        }

        if (!"".equals(name)) {//添加名字的模糊查询条件
            sql += " and name like '%" + name + "%'";
        }

        if (!"".equals(category)) {//添加分类查询条件
            sql += " and category = '" + category + "'";
        }


        if (!"".equals(minprice)) {//添加最小价格查询条件
            sql += " and minprice >= " + minprice;
        }
        if (!"".equals(maxprice)) {//添加最大价格查询条件
            sql += " and maxprice <= " + maxprice;
        }

        //执行查询
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageResult<Book> getBookListWithPageCount(int page) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        PageResult<Book> pr = new PageResult<>();
        //设置当前面
        pr.setCurrentPage(page);
        try{
            //总条数
            Long totalCount = (Long) qr.query("select count(*) from books where 1=1", new ScalarHandler());
            pr.setTotalCount(totalCount);

            //总页数
            int totalPage = (int) (totalCount % pr.getCountPerPage() == 0 ? totalCount / pr.getCountPerPage() : totalCount / pr.getCountPerPage() + 1);
            pr.setTotalPage(totalPage);

            //设置查询到的数据
            String sql = "select * from books where 1=1 order by name limit ?,?";//这里可以给个默认排序
            int start = pr.getCountPerPage() * (page - 1);
            List<Book> list = qr.query(sql, new BeanListHandler<Book>(Book.class), start, pr.getCountPerPage());
            pr.setList(list);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pr;
    }
}
