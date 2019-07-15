package com.fc.service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: fc
 * @Date: ${date} ${seconds}
 * @Description:
 */
public interface RedisService {
    /**
     * 设置键存活时间
     * @param key 键
     * @param seconds 时间,秒
     * @return
     */
    boolean expire(@NotNull String key, long seconds);

    /**
     * 获取键存活时间
     * @param key 键
     * @return 秒
     */
}
