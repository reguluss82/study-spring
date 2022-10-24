package com.oracle.oMVCBoard.command;

import org.springframework.ui.Model;

// excute serivce method 표준화
public interface BCommand {
	void execute(Model model);
}
