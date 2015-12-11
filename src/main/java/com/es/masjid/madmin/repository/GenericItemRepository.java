package com.es.masjid.madmin.repository;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by myachb on 10/6/2015.
 */
public interface GenericItemRepository extends JpaRepository<Item, Integer>, GenericItemRepositoryCustom{

//    @Query(value = "select i from Item i where i.validFrom <= :currentDate and i.validTo >= :currentDate and i.itemType = :itemType",
//            countQuery = "select count(i) from Item i where i.validFrom <= :currentDate and i.validTo >= :currentDate and i.itemType = :itemType")
//    Page<Item> findAllItems(@Param("currentDate") Date currentDate, @Param("itemType") ItemType itemType, Pageable pageable);

    @Query(value = "select i from Item i where i.itemType = :itemType",
            countQuery = "select count(i) from Item i where i.itemType = :itemType")
    Page<Item> findAllItems(@Param("currentDate") Date currentDate, @Param("itemType") ItemType itemType, Pageable pageable);

}


