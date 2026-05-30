package com.substring.easybuy.inventory.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.substring.easybuy.inventory.domain.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

	Optional<InventoryItem> findBySku(String sku);




	Optional<InventoryItem> findByProductId(UUID productId);



	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select i from InventoryItem i where i.id = :id")
	Optional<InventoryItem> findByIdForUpdate(@Param("id") Long id);





	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select i from InventoryItem i where i.productId = :productId")
	Optional<InventoryItem> findByProductIdForUpdate(@Param("productId") UUID productId);

	boolean existsBySku(String sku);

	boolean existsByProductId(UUID productId);

	List<InventoryItem> findByActiveTrueOrderByProductNameAsc();

	List<InventoryItem> findByAvailableQuantityLessThanEqualAndActiveTrueOrderByAvailableQuantityAsc(int threshold);
}

