package com.spring.base.util;

/**
 * @author xiao-_-wu
 * @date 2021/5/19 14:33
 */
public class TestUtil {

    public static void main(String[] args) {
        System.out.println(count(1));
    }

    private static String count(Integer x){
        int random ;
        System.out.println("第" + x + "天");
        random = (int)(Math.random() * 365 + 1);
        random %= (int)(Math.random() * 365 + 1);
        if (random == 0) {
            return "～(￣▽￣～)(～￣▽￣)～";
        }
        x += 1;
        return count(x);
    }

}
