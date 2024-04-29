package net.cocotea.admin.common.service.impl;

import net.cocotea.admin.common.service.IRedisService;
import org.noear.redisx.RedisClient;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;

import java.util.Collections;
import java.util.Set;

/**
 * @author CoCoTea
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Inject
    private RedisClient redisClient;

    @Override
    public void save(String key, String value, Long seconds){
        redisClient.open(session -> session.key(key).expire(Math.toIntExact(seconds)).set(value));
    }

    @Override
    public void saveByHours(String key, String value, int hours){
        save(key, value, hours * 3600L);
    }

    @Override
    public void saveByMinutes(String key, String value, int minutes){
        save(key, value, minutes * 60L);
    }

    @Override
    public void save(String key, String value) {
        redisClient.open(session -> session.key(key).set(value));
    }

    @Override
    public void delete(String key) {
        redisClient.open(session -> session.deleteKeys(Collections.singleton(key)));
    }

    @Override
    public void set(String key, String value) {
        redisClient.open(session -> session.key(key).expire(0).set(value));
    }

    @Override
    public String get(String key) {
        return redisClient.openAndGet(session -> session.key(key).get());
    }

    @Override
    public void hashPut(String key, String hashKey, String value) {
        redisClient.open(session -> session.key(key).hashSet(hashKey, value));
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisClient.openAndGet(session -> session.keys(pattern));
    }
}
