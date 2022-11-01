package com.oracle.oBootJpa03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa03.domain.item.Book;
import com.oracle.oBootJpa03.domain.item.Item;
import com.oracle.oBootJpa03.repository.InterItemRepository;
import com.oracle.oBootJpa03.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	private final ItemRepository itemRepository;
	private final InterItemRepository interItemRepository;
	public void saveItem(Item item) {
		itemRepository.itemSave(item);
		
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
		
	}

	public List<Item> findAll() {
		return interItemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	public Item findById(Long itemId) {
		//Optional<Item> selectedItem = interItemRepository.findById(itemId);
		//Item item = selectedItem.get();
		//return item;
		
		return interItemRepository.findById(itemId).get();
	}
	
	
}
