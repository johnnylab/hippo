package com.yangzai.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuvwaTest {
	@Test
	public void testRefreshAfterWrite() throws ExecutionException {
		LoadingCache<String, Long> cache = CacheBuilder.newBuilder().maximumSize(3)
				.refreshAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<String, Long>() {
					@Override
					public Long load(String key) throws Exception {
						Thread.sleep(2000);
						return System.currentTimeMillis();
					}
				});
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
		log.info(cache.get("john") + "");
	}
}
