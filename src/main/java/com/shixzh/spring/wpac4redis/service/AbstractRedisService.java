package com.shixzh.spring.wpac4redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class AbstractRedisService<K, V> implements IRedisService<K, V> {

	private RedisTemplate<K, V> redisTemplate;
	
	@Override
	public void set(final K key, final V value, final long expiredTime) {
		BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		if (expiredTime <= 0) {
			valueOper.set(value);
		} else {
			valueOper.set(value, expiredTime, TimeUnit.MICROSECONDS);
		}
	}

	@Override
	public V get(final K key) {
		BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);
		return valueOper.get();
	}

	@Override
	public Object getHash(K key, String name) {
		Object res = redisTemplate.boundHashOps(key).get(name);
		return res;
	}

	@Override
	public void del(K key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
	}
}
