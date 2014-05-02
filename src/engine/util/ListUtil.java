package engine.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static <T> ArrayList<ArrayList<T>> cartesianProduct(List<T>... sets) {
        if (sets.length < 2)
            throw new IllegalArgumentException(
                    "Can't have a product of fewer than two sets (got " +
                            sets.length + ")");

        return _cartesianProduct(0, sets);
    }

    private static <T> ArrayList<ArrayList<T>> _cartesianProduct(int index, List<T>... sets) {
        ArrayList<ArrayList<T>> ret = new ArrayList<ArrayList<T>>();
        if (index == sets.length) {
            ret.add(new ArrayList<T>());
        } else {
            for (T obj : sets[index]) {
                for (ArrayList<T> list : _cartesianProduct(index+1, sets)) {
                    list.add(obj);
                    ret.add(list);
                }
            }
        }
        return ret;
    }
}
