package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Pending_Status;

public class PendingStatusTypeHandler extends BaseTypeHandler<Pending_Status> {

	@Override
	public Pending_Status getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Pending_Status.valueOf(rs.getInt(columnName));
	}

	@Override
	public Pending_Status getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Pending_Status.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Pending_Status getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Pending_Status.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Pending_Status parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
		
	}
}
