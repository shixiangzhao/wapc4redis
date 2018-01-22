package com.shixzh.spring.wpac4redis.service;

public interface IRedisService<K, V> {
	void set(K key, V value, long expiredTime);
	V get(K key);
	Object getHash(K key, String name);
	void del(K key);
}
