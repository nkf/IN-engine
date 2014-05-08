package engine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListUtil {

    public static <T> List<List<T>> cartesianProduct(T[][] sets) {
        if (sets.length < 2)
            throw new IllegalArgumentException(
                    "Can't have a product of fewer than two sets (got " +
                            sets.length + ")");
        List<T>[] lists = mapCastList(sets);
        return _cartesianProduct(0, lists);
    }

    private static <T> List<List<T>> _cartesianProduct(int index, List<T>... sets) {
        List<List<T>> ret = new ArrayList<List<T>>();
        if (index == sets.length) {
            ret.add(new LinkedList<T>());
        } else {
            for (T obj : sets[index]) {
                for (List<T> list : _cartesianProduct(index+1, sets)) {
                    list.add(0,obj);
                    ret.add(list);
                }
            }
        }
        return ret;
    }
    public static <T> List<List<T>> toList(T[][] arrays) {
        return Arrays.asList(mapCastList(arrays));
    }
    public static <T> List<T>[] mapCastList(T[][] arrays) {
        List<T>[] lists = new List[arrays.length];
        for (int i = 0; i <arrays.length; i++) {
            lists[i] = Arrays.asList(arrays[i]);
        }
        return lists;
    }


}
