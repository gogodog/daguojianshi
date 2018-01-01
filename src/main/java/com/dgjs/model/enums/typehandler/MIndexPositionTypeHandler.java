package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.M_Index_Position;

public class MIndexPositionTypeHandler extends BaseTypeHandler<M_Index_Position> {

	@Override
	public M_Index_Position getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return M_Index_Position.valueOf(rs.getInt(columnName));
	}

	@Override
	public M_Index_Position getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return M_Index_Position.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public M_Index_Position getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return M_Index_Position.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			M_Index_Position parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}

