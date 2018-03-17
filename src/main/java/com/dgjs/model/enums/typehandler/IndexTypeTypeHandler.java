package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Index_Type;

public class IndexTypeTypeHandler  extends BaseTypeHandler<Index_Type> {

	@Override
	public Index_Type getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Index_Type.valueOf(rs.getInt(columnName));
	}

	@Override
	public Index_Type getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Index_Type.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Index_Type getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Index_Type.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Index_Type parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}
