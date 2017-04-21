package com.lb.core.executor.handler;

import com.lb.core.executor.handler.impl.DefaultExecutorServiceHandler;
import com.lb.core.executor.handler.impl.DefaultJobExceptionHandler;
import com.lb.core.util.json.GsonFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作业属性配置
 * Created by libo on 2017/4/21.
 */
public final class JobProperties {
    private EnumMap<JobPropertiesEnum, String> map = new EnumMap<>(JobPropertiesEnum.class);

    /**
     * 设置作业属性.
     *
     * @param key 属性键
     * @param value 属性值
     */
    public void put(final String key, final String value) {
        JobPropertiesEnum jobPropertiesEnum = JobPropertiesEnum.from(key);
        if (null == jobPropertiesEnum || null == value) {
            return;
        }
        map.put(jobPropertiesEnum, value);
    }

    /**
     * 获取作业属性.
     *
     * @param jobPropertiesEnum 作业属性枚举
     * @return 属性值
     */
    public String get(final JobPropertiesEnum jobPropertiesEnum) {
        return map.containsKey(jobPropertiesEnum) ? map.get(jobPropertiesEnum) : jobPropertiesEnum.getDefaultValue();
    }

    /**
     * 获取所有键.
     *
     * @return 键集合
     */
    public String json() {
        Map<String, String> jsonMap = new LinkedHashMap<>(JobPropertiesEnum.values().length, 1);
        for (JobPropertiesEnum each : JobPropertiesEnum.values()) {
            jsonMap.put(each.getKey(), get(each));
        }
        return GsonFactory.getGson().toJson(jsonMap);
    }

    /**
     * 作业属性枚举.
     */
    @RequiredArgsConstructor
    @Getter
    public enum JobPropertiesEnum {

        /**
         * 作业异常处理器.
         */
        JOB_EXCEPTION_HANDLER("job_exception_handler", JobExceptionHandler.class, DefaultJobExceptionHandler.class.getCanonicalName()),

        /**
         * 线程池服务处理器.
         */
        EXECUTOR_SERVICE_HANDLER("executor_service_handler", ExecutorServiceHandler.class, DefaultExecutorServiceHandler.class.getCanonicalName());

        private final String key;

        private final Class<?> classType;

        private final String defaultValue;

        /**
         * 通过属性键获取枚举.
         *
         * @param key 属性键
         * @return 枚举
         */
        public static JobPropertiesEnum from(final String key) {
            for (JobPropertiesEnum each : JobPropertiesEnum.values()) {
                if (each.getKey().equals(key)) {
                    return each;
                }
            }
            return null;
        }
    }
}
