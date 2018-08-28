package com.yangzai.cache;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CaffeineTest {
	@Test
	public void testRefreshAfterWrite(){
		LoadingCache<String, Long> cache = Caffeine.newBuilder()
				  .maximumSize(100)
				  //2秒后更新一次
				  .refreshAfterWrite(2, TimeUnit.SECONDS)
				  .build(new CacheLoader<String,Long>(){
					@Override
					public Long load(String key) throws Exception {
						Thread.sleep(2000);
						return System.currentTimeMillis();
					}
				  });
		
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
	}
	@Test
	public void testRewriteAfterWrite(){
		LoadingCache<String, Long> cache = Caffeine.newBuilder()
				  .maximumSize(100)
				  //每1秒更新一次
				  .expireAfterWrite(5, TimeUnit.SECONDS)
				  .build(new CacheLoader<String,Long>(){
					@Override
					public Long load(String key) throws Exception {
						Thread.sleep(2000);
						return System.currentTimeMillis();
					}
				  });
		
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
		log.info(cache.get("john")+"");
	}
}
