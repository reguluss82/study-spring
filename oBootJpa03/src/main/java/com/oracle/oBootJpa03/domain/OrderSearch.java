package com.oracle.oBootJpa03.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
	private String      memberName;		//회원이름
	private OrderStatus orderStatus;	//주문상태[ORDER , CANCLE]

}
