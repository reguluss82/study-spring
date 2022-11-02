package com.oracle.oBootJpa03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa03.domain.item.Book;
import com.oracle.oBootJpa03.domain.item.Item;
import com.oracle.oBootJpa03.form.BookForm;
import com.oracle.oBootJpa03.repository.InterItemRepository;
import com.oracle.oBootJpa03.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //성능을 위해 Transactional 할 곳에만 @를 건다
public class ItemService {
	private final ItemRepository itemRepository;
	private final InterItemRepository interItemRepository;
//	public void saveItem(Item item) {
//		itemRepository.itemSave(item);
//		
//	}
	@Transactional
	public void saveItem(Item item) {
		interItemRepository.save(item);
		
	}
	public List<Item> findItems() {
		return itemRepository.findAll();
		
	}

	public List<Item> findAll() {
		return interItemRepository.findAll();
	}
	
	//EntityManager 사용
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
	
	//Method 이름으로 query 생성 사용
	public Item findById(Long itemId) {
		//Optional<Item> selectedItem = interItemRepository.findById(itemId); //Optional 은 return이 boolean, get()으로 객체 받기
		//Item item = selectedItem.get();
		//return item;
		
		return interItemRepository.findById(itemId).get();
	}
	
	//@Query 사용
	public Item findByIdQuery(Long itemId) {
		return interItemRepository.findByIdQuery(itemId);
	}
	
	@Transactional
	public void updateItem(BookForm form) {
		//Book book = (Book) itemRepository.findOne(form.getId());
		Book book = (Book) interItemRepository.findById(form.getId()).get();
		
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setAuthor(form.getAuthor());
		book.setStockQuantity(form.getStockQuantity());
		book.setIsbn(form.getIsbn());
		//영속성 관리에 의해 setter만 해줘도 값 update된다.
	}
	
	
}
