package com.dgjs.model.enums.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dgjs.model.enums.Carousel_Position;

public class CarouselPositionTypeHandler  extends BaseTypeHandler<Carousel_Position> {

	@Override
	public Carousel_Position getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return Carousel_Position.valueOf(rs.getInt(columnName));
	}

	@Override
	public Carousel_Position getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return Carousel_Position.valueOf(rs.getInt(columnIndex));
	}

	@Override
	public Carousel_Position getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return Carousel_Position.valueOf(cs.getInt(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex,
			Carousel_Position parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(parameterIndex, parameter.getKey());
	}

}


