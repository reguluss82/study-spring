package com.oracle.oBootJpa03.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa03.domain.item.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;
	
	public void itemSave(Item item) {
		em.persist(item);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i" , Item.class).getResultList();
	}
	
	public Item findOne(Long itemId) {
		return em.find(Item.class, itemId);
	}
}
