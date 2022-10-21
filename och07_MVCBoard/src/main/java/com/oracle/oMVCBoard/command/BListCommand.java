package com.oracle.oMVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

// Service
public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// Dao 연결
		BDao dao = new BDao();
		ArrayList<BDto> boardDtoList;
			boardDtoList = dao.boardList();
			System.out.println("BlistCommand boardDtoList.size() -> " + boardDtoList.size());
			model.addAttribute("boardList", boardDtoList);

	}

}
