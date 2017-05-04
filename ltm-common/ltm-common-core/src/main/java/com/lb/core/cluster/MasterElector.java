package com.lb.core.cluster;

import com.lb.core.AppContext;
import com.lb.core.constant.EcTopic;
import com.lb.core.ec.EventInfo;
import com.lb.core.listener.MasterChangeListener;
import com.lb.core.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Master节点选举
 * Created by libo on 2017/5/4.
 *    策略:  从follower节点中选取Master节点，
 *          由节点创建时间来决定(创建时间最小的便是Master，即最早创建的额是Master)
 *          当Master挂掉之后，要重新选举
 */
@Slf4j
public class MasterElector {
    private AppContext appContext;
    private List<MasterChangeListener> listeners;
    private Node master;
    private ReentrantLock lock = new ReentrantLock();

    public MasterElector(AppContext appContext) {
        this.appContext = appContext;
    }

    public void addMasterChangeListener(List<MasterChangeListener> masterChangeListeners){
        if (listeners == null)
            listeners = new CopyOnWriteArrayList<>();
        if (CollectionUtils.isNotEmpty(masterChangeListeners)){
            listeners.addAll(masterChangeListeners);
        }
    }

    public void addNodes(List<Node> nodes){
        lock.lock();
        try {
            Node newMster = null;
            for (Node node : nodes) {
                if (newMster == null){
                    newMster = node;
                }else {
                    if (newMster.getCreateTime() > node.getCreateTime())
                        newMster = node;
                }
            }
            addNode(newMster);
        }finally {
            lock.unlock();
        }
    }

    public void removeNode(List<Node> removedNodes){
        lock.lock();
        try{
            if (master != null){
                boolean masterRemoved = false;
                for (Node removedNode : removedNodes) {
                    if (master.getIdentity().equals(removedNode.getIdentity())){
                        masterRemoved = true;
                    }
                }
                if (masterRemoved){
                    List<Node> nodes = appContext.getSubscribedNodeManager().getNodeList(appContext.getConfig().getNodeType(),
                            appContext.getConfig().getNodeGroup());
                    if (CollectionUtils.isNotEmpty(nodes)){
                        Node newMaster = null;
                        for (Node node : nodes) {
                            if (newMaster == null)
                                newMaster = node;
                            else {
                                if (newMaster.getCreateTime() > node.getCreateTime())
                                    newMaster = node;
                            }
                        }
                        master = newMaster;
                        notifyListener();
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * 判断当前节点是否是Master节点
     * @return
     */
    public boolean isCurrentMaster(){
        return master != null && master.getIdentity().equals(appContext.getConfig().getIdentity());
    }

    private void addNode(Node node){
        lock.lock();
        try {
            if (master == null){
                master = node;
                notifyListener();
            }else {
                if (master.getCreateTime() > node.getCreateTime()){
                    master = node;
                    notifyListener();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void notifyListener(){
        boolean isMaster = false;
        if (appContext.getConfig().getIdentity().equals(master.getIdentity())){
            log.info("Current node become the master node:{}", master);
            isMaster = true;
        } else {
            log.info("Master node is : {}", master);
            isMaster = false;
        }

        if (listeners != null){
            for (MasterChangeListener masterChangeListener : listeners) {
                try {
                    masterChangeListener.change(master, isMaster);
                } catch (Throwable t){
                    log.error("MasterChangeListener notify error!", t);
                }
            }
        }
        EventInfo eventInfo = new EventInfo(EcTopic.MASTER_CHANGED);
        eventInfo.setParam("master", master);
        appContext.getEventCenter().publishSync(eventInfo);
    }
}
