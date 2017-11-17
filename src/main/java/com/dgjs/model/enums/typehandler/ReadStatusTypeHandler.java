package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Read_Status;

public class ReadStatusTypeHandler extends BaseTypeHandler<Read_Status> {

	@Override
	public Read_Status getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Read_Status.valueOf(rs.getInt(columnName));
	}

	@Override
	public Read_Status getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Read_Status.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Read_Status getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Read_Status.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Read_Status parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
		
	}
}
