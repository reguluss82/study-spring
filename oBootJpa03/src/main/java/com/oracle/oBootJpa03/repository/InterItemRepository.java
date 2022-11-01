package com.oracle.oBootJpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.oBootJpa03.domain.item.Item;

public interface InterItemRepository extends JpaRepository<Item, Long>{
	//public Item findById(Long itemId);
}
