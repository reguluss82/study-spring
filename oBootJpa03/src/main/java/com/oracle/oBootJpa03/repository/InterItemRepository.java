package com.oracle.oBootJpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oracle.oBootJpa03.domain.item.Item;

public interface InterItemRepository extends JpaRepository<Item, Long>{
	@Query(value = "select i from Item i where id = :id")
	public Item findByIdQuery(@Param("id") Long itemId);
}
