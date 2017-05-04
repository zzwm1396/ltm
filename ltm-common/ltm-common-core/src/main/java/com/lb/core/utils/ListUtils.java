package com.lb.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * list集合过滤器
 * Created by libo on 2017/5/4.
 */
public class ListUtils {

    public static <E> List<E> filter(final List<E> list, Filter<E> filter){
        List<E> newList = new ArrayList<>();
        if (list != null && list.size() != 0){
            for (E e : list) {
                if (filter.filter(e)){
                    newList.add(e);
                }
            }
        }
        return newList;
    }

    public interface Filter<E>{
        public boolean filter(E e);
    }
}
