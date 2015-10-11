package com.es.masjid.madmin.repository;

import com.es.masjid.madmin.model.Item;
import com.es.masjid.madmin.model.ItemType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by myachb on 10/6/2015.
 */
public class GenericItemRepositoryImpl implements GenericItemRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    /**
     * This list includes current + future valid items
     * @param itemType
     * @return
     */
    public List<Item> getCurrentAndFutureValidItemsByType(ItemType itemType) {

        @SuppressWarnings("JpaQlInspection") TypedQuery<Item> query = em.createQuery("select i from Item i where i.validTo >= :currentDate and i.itemType = :itemType", Item.class);
        return query.setParameter("currentDate", new Date()).setParameter("itemType", itemType).getResultList();
    }

    /**
     * This list includes only the current valid items excluding the future valid items
     * @param itemType
     * @return
     */
    public List<Item> getCurrentValidItemsByType(ItemType itemType){
        @SuppressWarnings("JpaQlInspection") TypedQuery<Item> query = em.createQuery("select i from Item i where i.validFrom <= :currentDate and i.validTo >= :currentDate and i.itemType = :itemType", Item.class);
        return query.setParameter("currentDate", new Date()).setParameter("itemType", itemType).getResultList();

    }

}
