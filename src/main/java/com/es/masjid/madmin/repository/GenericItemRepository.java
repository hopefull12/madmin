package com.es.masjid.madmin.repository;

import com.es.masjid.madmin.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by myachb on 10/6/2015.
 */
public interface GenericItemRepository extends JpaRepository<Item, Integer>, GenericItemRepositoryCustom{
}
