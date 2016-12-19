package core.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.store.LruPolicy;

/**
 * Created by guorui on 2016/12/15.
 */
public class TestEhcache {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.create(ClassLoader.getSystemResourceAsStream("ehcache-config.xml"));
        // 自定义缓存对象，lru支持持久化到磁盘，12小时没有访问，缓存失效，最长24小时失效
        Cache cache = new Cache("testCache", 5, true, false, 86400, 43200, true, 120);
        cacheManager.addCache(cache);
        cache.bootstrap();
        cache.setMemoryStoreEvictionPolicy(new LruPolicy());

        Cache loadCache = cacheManager.getCache("testCache");
        loadCache.put(new Element("test1", "test1"));
        loadCache.put(new Element("test2", "test2"));
        loadCache.put(new Element("test3", "test3"));
        loadCache.put(new Element("test4", "test4"));
        loadCache.put(new Element("test5", "test5"));
        loadCache.put(new Element("test6", "test6"));
        loadCache.flush();
        System.out.println(loadCache.getSize());
    }
}
