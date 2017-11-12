package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.OperateEnum;

public class OperateEnumTypeHandler extends BaseTypeHandler<OperateEnum> {

	@Override
	public OperateEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return OperateEnum.valueOf(rs.getInt(columnName));
	}

	@Override
	public OperateEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return OperateEnum.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public OperateEnum getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return OperateEnum.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			OperateEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
		
	}
}
