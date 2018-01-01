package com.dgjs.model.persistence.condition;

import java.util.List;

import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.M_Index_Position;
import com.dgjs.model.enums.UpDown_Status;

public class MIndexConfigCondition {

	private Index_Type type;//类型
	private UpDown_Status status;//状态
	private List<M_Index_Position> positions;//位置 
	private List<Integer> sorts;//排序
	
	public Index_Type getType() {
		return type;
	}
	public void setType(Index_Type type) {
		this.type = type;
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public List<M_Index_Position> getPositions() {
		return positions;
	}
	public void setPositions(List<M_Index_Position> positions) {
		this.positions = positions;
	}
	public List<Integer> getSorts() {
		return sorts;
	}
	public void setSorts(List<Integer> sorts) {
		this.sorts = sorts;
	}
	
}
