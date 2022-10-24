package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// model 을 map으로 전환
		Map<String, Object> map = model.asMap();
		// controller에서 보내준 "request" key (String) 의 value request(Object)
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId"); //BCommand interface 의 rule을 따라가야하므로 Model로 받는 방법을 사용. 따라서 model에 request를 넣어서 받음
		
		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		
		model.addAttribute("mvc_board", board);
	}

}
