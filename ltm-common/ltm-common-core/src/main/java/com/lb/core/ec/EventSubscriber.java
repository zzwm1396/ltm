package com.lb.core.ec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Observer;

/**
 * 事件订阅者
 * Created by libo on 2017/4/28.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventSubscriber {

    private String id;

    private Observer observer;
}
