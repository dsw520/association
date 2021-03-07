package cn.lkq520.shiro.cache;

import cn.lkq520.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2020/12/16 12:13
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache(){}

    public RedisCache(String cacheName){
        this.cacheName = cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key:"+k);
        return (V) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key:"+k);
        System.out.println("put value:"+v);
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("remove:"+k);
        getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("clear");
        getRedisTemplate().opsForHash().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
