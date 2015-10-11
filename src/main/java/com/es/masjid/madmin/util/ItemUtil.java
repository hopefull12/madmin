package com.es.masjid.madmin.util;

import com.es.masjid.madmin.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myachb on 10/9/2015.
 */
public class ItemUtil {

    public static List<Item> safe( List other ) {
        return other == null ? new ArrayList<Item>() : other;
    }
}
