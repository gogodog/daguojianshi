package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Ad_Position;

public class AdPositionTypeHandler extends BaseTypeHandler<Ad_Position> {

	@Override
	public Ad_Position getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Ad_Position.valueOf(rs.getInt(columnName));
	}

	@Override
	public Ad_Position getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Ad_Position.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Ad_Position getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Ad_Position.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Ad_Position parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}


