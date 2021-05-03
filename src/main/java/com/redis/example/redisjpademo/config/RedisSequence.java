package com.redis.example.redisjpademo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisSequence implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Long getSequence() {
        String caller = getCallerClass(2).getSimpleName()+"Sequence";
        String[] beanNames = applicationContext.getBeanNamesForType(ResolvableType.forType(new ParameterizedTypeReference<RedisTemplate>() {}));
        if (beanNames.length > 0) {
            RedisTemplate bean = (RedisTemplate) applicationContext.getBean(beanNames[0]);
            ValueOperations<String, Object> valueOper = bean.opsForValue();
            valueOper.setIfAbsent(caller, 101);
            return getIncrement(caller, valueOper);
        }
        return 0l;
    }

    private synchronized  static Long getIncrement(String caller, ValueOperations<String, Object> valueOper) {
        return valueOper.increment(caller, 1);
    }

    public static Class getCallerClass(int level)  {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String rawFQN = stElements[level+1].toString().split("\\(")[0];
        try {
            return Class.forName(rawFQN.substring(0, rawFQN.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
