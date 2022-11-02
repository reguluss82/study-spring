package com.oracle.oBootJpa03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.oBootJpa03.domain.Member;
import com.oracle.oBootJpa03.domain.Order;
import com.oracle.oBootJpa03.domain.OrderSearch;
import com.oracle.oBootJpa03.domain.item.Item;
import com.oracle.oBootJpa03.service.ItemService;
import com.oracle.oBootJpa03.service.MemberService;
import com.oracle.oBootJpa03.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {
	private final OrderService  orderService;
	private final MemberService memberService;
	private final ItemService   itemService;
	
	@GetMapping(value = "order")
	public String createOrderForm(Model model) {
		//List<Member> members = memberService.findMembers();
		//List<Item>   items   = itemService.findItems();
		List<Member> members = memberService.findAll();
		List<Item>   items   = itemService.findAll();
		model.addAttribute("members", members);
		model.addAttribute("items",   items);
		return "order/orderForm";
	}
	
	@PostMapping(value = "orderSave")
	public String orderSave(@RequestParam("memberId") Long memberId,
							@RequestParam("itemId")   Long itemId,
							@RequestParam("count")    int  count) {
		orderService.order(memberId, itemId, count);
		return "redirect:/";
	}
	
	@GetMapping("orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch ,  Model model) {
		List<Order> orders = orderService.findOrders();
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
}
