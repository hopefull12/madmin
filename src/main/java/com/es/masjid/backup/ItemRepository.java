package com.es.masjid.backup;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.masjid.madmin.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemRepositoryCustom {

}
