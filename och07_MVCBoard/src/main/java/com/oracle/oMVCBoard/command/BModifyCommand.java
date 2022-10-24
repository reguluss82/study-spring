package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. model Map선언
		Map<String, Object> map = model.asMap();
		// 2. parameter -> bId , bName , bTitle , bContent
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDao dao = new BDao();
		dao.modify(bId, bName, bTitle, bContent);

	}

}
