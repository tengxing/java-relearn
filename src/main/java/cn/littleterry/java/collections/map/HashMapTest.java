package cn.littleterry.java.collections.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author terry
 * @since 05/06/2019
 */
public class HashMapTest {
    public static void main(String[] args) {
        /**
         * 能否为null
         */
        enableNone();
        /**
         * 线程安全
         */
        isSecurity();
    }

    /**
     * HashMap 底层采用数组+链表实现 线程不安全；
     * Hashtable 底层采用数组+链表实现 线程安全，读写操作均使用synchronized
     * 关键字锁住方法put(),get()，线程独占，锁独占，效率低；
     * ConcurrentHashMap 底层采用分段的数组+链表实现 线程安全，
     * 分段数组的设计厉害之处在于 锁分离技术 ，可以Map分为N个Segment，这
     * 样在写操作支持多个修改操作并发进行，读操作不加锁，但是HashEntry
     * 的value变量是volatile的，可以保证读取到最新值，效率自然提升
     * 不少，范围1 << 30，2的幂数，默认16倍，可以查源码CAPACITY，是
     * Hashtable的优化拓展。
     *
     * 分离锁技术:在某些情况下，可以将锁分解技术进一步扩展为对一组独立对象上的锁进行分解，这种情况称为锁分段。
     * 首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
     * 例如ConcurrencyHashMap是有一个包含16个锁的数组实现，每个锁保护所有散列桶的1/16，其中第N个散列桶由第（N mod 16）个锁来保护。假设所有关键字都时间均与分布，那么相当于把锁的请求减少到原来的1/16，可以支持多达16个的并发写入。
     */
    private static void isSecurity() {
        //按住ctrl键，鼠标点击方法即可看实现，putVal();
        HashMap hm = new HashMap();
        hm.put("lable","terry");
        hm.get("lable");
        Hashtable ht = new Hashtable();
        ht.put("lable","terry");
        ht.get("lable");
        ConcurrentHashMap chm = new ConcurrentHashMap();
        chm.put("lable","terry");
        chm.get("lable");
        //todo 测试用例
    }

    /**
     * HashMap可以，ConcurrentHashMap，Hashtable不能，否则报错空指针
     */
    public static void enableNone(){
        Map hm = new HashMap();
        hm.put(null,null);
        Map chm = new ConcurrentHashMap();
        //chm.put(null,null);//NullPointerException
        Map ht = new Hashtable();
        //ht.put(null,null);//NullPointerException
    }
}
