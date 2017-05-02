package com.lb.core.constant;

import java.util.regex.Pattern;

/**
 * 系统配置常量
 * Created by libo on 2017/4/28.
 */
public interface Constants {

    // 以逗号截取字符串，忽略前后空格
    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

    // 可用处理器个数
    int AVAILABLE_PROCESSOR = Runtime.getRuntime().availableProcessors();
}
