package com.lb.core.ec;

/**
 * 事件中心接口
 * Created by libo on 2017/4/28.
 */
public interface EventCenter {
    /**
     * 订阅主题
     * @param subscriber 订阅者
     * @param topics 主题
     */
    void subscribe(EventSubscriber subscriber, String... topics);

    /**
     * 取消订阅主题
     * @param subscriber 订阅者
     * @param topic 主题
     */
    void unSubscribe(EventSubscriber subscriber, String topic);

    /**
     * 同步发布主题信息
     * @param eventInfo 事件信息
     */
    void publishSync(EventInfo eventInfo);

    /**
     * 异步发布主题信息
     * @param eventInfo 事件信息
     */
    void publishAsync(EventInfo eventInfo);
}
