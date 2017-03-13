package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Articlescrap_Type;

public class ArticlescrapTypeTypeHandler extends BaseTypeHandler<Articlescrap_Type> {

	@Override
	public Articlescrap_Type getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Articlescrap_Type.valueOf(rs.getInt(columnName));
	}

	@Override
	public Articlescrap_Type getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Articlescrap_Type.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Articlescrap_Type getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Articlescrap_Type.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Articlescrap_Type parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}

