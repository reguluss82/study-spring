package com.oracle.oBootJpa03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa03.domain.item.Book;
import com.oracle.oBootJpa03.domain.item.Item;
import com.oracle.oBootJpa03.form.BookForm;
import com.oracle.oBootJpa03.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	
	@GetMapping(value = "/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}
	
	@PostMapping(value = "/items/save")
	public String itemSave(BookForm form) {
		Book book = new Book();
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		itemService.saveItem(book);
		return "redirect:/";
	}
	
	@GetMapping(value = "/items")
	public String itemList(Model model) {
	//	List<Item> items = itemService.findItems();
		List<Item> items = itemService.findAll();
		
		model.addAttribute("items", items);
		return "items/itemList";
	}
	
	/**
	 * 상품 수정 폼
	 */
	@GetMapping(value = "/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId , Model model) {
		//Book item = (Book) itemService.findOne(itemId); // EntityManager사용
		//Book item = (Book) itemService.findById(itemId); // method 이름으로 query생성
		Book item = (Book) itemService.findByIdQuery(itemId); // @Query 사용
		BookForm form = new BookForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		form.setAuthor(item.getAuthor());
		form.setIsbn(item.getIsbn());
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	
	@PostMapping(value = "/items/{itemId}/edit")
	public String updateItem(@ModelAttribute("form") BookForm form) {
		//itemService.updateItem(form.getId() , form.getName() , form.getPrice());
		
		itemService.updateItem(form);
		return "redirect:/items";
	}
}
