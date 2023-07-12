package edu.kh.dgc.common.utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisUtil {     //redis 기본적인 CRUD 로직
    private final StringRedisTemplate template;
 
    public String getData(String key) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        return valueOperations.get(key);
    }
 
    public boolean existData(String key) {
        return Boolean.TRUE.equals(template.hasKey(key));
    }
    
    public boolean hexists(String key, String field) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        return hashOperations.hasKey(key, field);
    }
    
    public void setDataExpire(String key, String value, long duration) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }
    
    public void hset(String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        hashOperations.put(key, hashKey, value);
        template.expire(key, 60 * 5L, TimeUnit.SECONDS);
    }
    
    public String hget(String key, String hashKey) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        return hashOperations.get(key, hashKey);
    }
    
    public boolean isValidAuthenticationCode(String authenticationCode, String phoneNumber) {
        RedisUtil redisUtil = new RedisUtil(template); // RedisUtil 인스턴스 생성
        String storedPhoneNumber = (String) redisUtil.getData(authenticationCode);
        return phoneNumber.equals(storedPhoneNumber);
    }
    
    public void deleteData(String key) {
        template.delete(key);
    }
    
    public void hdelete(String key, String fields) {
        HashOperations<String, String, String> hashOperations = template.opsForHash();
        hashOperations.delete(key, fields);
    }
}
