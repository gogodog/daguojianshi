package com.dgjs.service.transaction;

public interface InvitationCodeTransactionService {

	public void consume(Integer toUserId,String code);
}
