package com.lb.core.cluster;

import com.lb.core.AppContext;
import com.lb.core.commons.ConcurrentHashSet;
import com.lb.core.constant.EcTopic;
import com.lb.core.ec.EventInfo;
import com.lb.core.listener.NodeChangeListener;
import com.lb.core.node.Node;
import com.lb.core.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 节点管理(用于管理自己关注的节点)
 * Created by libo on 2017/5/4.
 */
@Slf4j
public class SubscribedNodeManager implements NodeChangeListener {

    private final ConcurrentHashMap<NodeType, Set<Node>> NODES = new ConcurrentHashMap<>();

    private AppContext appContext;

    public SubscribedNodeManager(AppContext appContext) {
        this.appContext = appContext;
    }

    private void addNode(Node node){
        Set<Node> nodeSet = NODES.get(node.getNodeType());
        if (CollectionUtils.isEmpty(nodeSet)){
            nodeSet = new ConcurrentHashSet<>();
            Set<Node> oldNodeSet = NODES.putIfAbsent(node.getNodeType(), nodeSet);
            if (oldNodeSet != null){
                nodeSet = oldNodeSet;
            }
        }
        nodeSet.add(node);
        EventInfo eventInfo = new EventInfo(EcTopic.NODE_ADD);
        eventInfo.setParam("node", node);
        appContext.getEventCenter().publishSync(eventInfo);
        log.info("Add{}", node);
    }

    private void removeNode(Node delNode){
        Set<Node> nodeSet = NODES.get(delNode.getNodeType());
        if (CollectionUtils.isNotEmpty(nodeSet)){
            for (Node node: nodeSet) {
                if (node.getIdentity().equals(delNode.getIdentity())){
                    nodeSet.remove(node);
                    EventInfo eventInfo = new EventInfo(EcTopic.NODE_REMOVE);
                    eventInfo.setParam("node", node);
                    appContext.getEventCenter().publishSync(eventInfo);
                    log.info("Remove {}", node);
                }
            }
        }
    }

    /**
     * 获取被关注的节点集合
     * @param nodeType 节点类型
     * @param nodeGroup 节点组
     * @return 被关注的节点集合
     */
    public List<Node> getNodeList(final NodeType nodeType, final String nodeGroup){
        return ListUtils.filter(this.getNodeList(nodeType), new ListUtils.Filter<Node>() {
            @Override
            public boolean filter(Node node) {
                return node.getGroup().equals(nodeGroup);
            }
        });
    }

    /**
     * 获取关注的节点集合
     * @param nodeType 节点类型
     * @return 被关注的节点集合
     */
    public List<Node> getNodeList(NodeType nodeType){
        return new ArrayList<Node>(NODES.get(nodeType));
    }

    @Override
    public void addNodes(List<Node> nodes) {
        if (CollectionUtils.isEmpty(nodes))
            return;
        for (Node node : nodes) {
            addNode(node);
        }
    }

    @Override
    public void removeNodes(List<Node> nodes) {
        if (CollectionUtils.isEmpty(nodes))
            return;
        for (Node node : nodes) {
            removeNode(node);
        }
    }
}
