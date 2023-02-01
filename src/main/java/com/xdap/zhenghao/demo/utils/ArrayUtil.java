package com.xdap.zhenghao.demo.utils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
/**
 * @author:杨智
 * @create: 2023-02-01 08:58
 * @Description: 这是一个数组处理工具类
 */
@Component
public class ArrayUtil {

    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 9:05
    * @Description: 参数类型是String[]
    * @return: ArrayList<String[]>
    */

    public ArrayList<String[]> splitArray(String[] array, int maxNum) {
        ArrayList<String[]> result = new ArrayList<>();
        if (array == null || array.length == 0) {
            return result;
        }
        int index = 0;
        while (index < array.length) {
            int len = Math.min(maxNum, array.length - index);
            String[] subArray = new String[len];
            System.arraycopy(array, index, subArray, 0, len);
            result.add(subArray);
            index += len;
        }
        return result;
    }

    /*
    * @Author: yangzhi
    * @Date: 2023/2/1 9:05
    * @Description: 参数类型是 ArrayList<String>
    * @return: ArrayList<String[]>
    */

    public  ArrayList<String[]> splitArray(ArrayList<String> dataList, int maxSize) {
        ArrayList<String[]> result = new ArrayList<>();
        if (dataList == null || dataList.isEmpty() || maxSize <= 0) {
            return result;
        }
        int index = 0;
        int count = dataList.size() / maxSize;
        for (int i = 0; i < count; i++) {
            String[] subArray = new String[maxSize];
            for (int j = 0; j < maxSize; j++) {
                subArray[j] = dataList.get(index++);
            }
            result.add(subArray);
        }
        if (index < dataList.size()) {
            int size = dataList.size() - index;
            String[] subArray = new String[size];
            for (int i = 0; i < size; i++) {
                subArray[i] = dataList.get(index++);
            }
            result.add(subArray);
        }
        return result;
    }
}
