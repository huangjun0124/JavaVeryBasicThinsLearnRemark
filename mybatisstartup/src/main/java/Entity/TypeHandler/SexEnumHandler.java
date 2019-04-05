package Entity.TypeHandler;

import Definitions.AccountTypeEnum;
import Definitions.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumHandler implements TypeHandler<SexEnum> {
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sexEnum.getId());
    }

    public SexEnum getResult(ResultSet resultSet, String colName) throws SQLException {
        int id = resultSet.getInt(colName);
        return SexEnum.getSexById(id);
    }

    public SexEnum getResult(ResultSet resultSet, int colIndex) throws SQLException {
        int id = resultSet.getInt(colIndex);
        return SexEnum.getSexById(id);
    }

    public SexEnum getResult(CallableStatement callableStatement, int colIndex) throws SQLException {
        int id = callableStatement.getInt(colIndex);
        return SexEnum.getSexById(id);
    }
}
