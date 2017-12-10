package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Message_Related_Type;

public class MessageRelatedTypeHandler extends BaseTypeHandler<Message_Related_Type> {

	@Override
	public Message_Related_Type getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Message_Related_Type.valueOf(rs.getInt(columnName));
	}

	@Override
	public Message_Related_Type getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Message_Related_Type.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Message_Related_Type getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Message_Related_Type.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Message_Related_Type parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}

