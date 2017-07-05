package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Judge_Level;

public class JudgeLevelTypeHandler extends BaseTypeHandler<Judge_Level> {

	@Override
	public Judge_Level getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Judge_Level.valueOf(rs.getInt(columnName));
	}

	@Override
	public Judge_Level getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Judge_Level.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Judge_Level getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Judge_Level.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Judge_Level parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
		
	}

}


