package com.lb.core.cmd;

import com.lb.core.constant.Constants;
import com.lb.core.utils.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HttpCmd 接收器
 * Created by libo on 2017/5/2.
 */
@Slf4j
public class HttpCmdAcceptor {

    private final AtomicBoolean start = new AtomicBoolean(false);

    private final ExecutorService executorService;

    private ServerSocket serverSocket;

    private Thread thread;

    private HttpCmdContext context;

    public HttpCmdAcceptor(ServerSocket serverSocket, HttpCmdContext context){
        this.context = context;
        this.serverSocket = serverSocket;
        this.executorService = new ThreadPoolExecutor(Constants.AVAILABLE_PROCESSOR,
                Constants.AVAILABLE_PROCESSOR, 0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>(100),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    public void start(){
        if (!start.compareAndSet(false, true)){
            return;
        }

        if (thread == null){
            this.thread = new NamedThreadFactory("HTTP-CMD-AAEPTOR",true).newThread(
                    new Runnable() {
                        @Override
                        public void run() {

                            while (isStarted()){
                                try {
                                    Socket socket = serverSocket.accept();
                                    if (socket == null){
                                        continue;
                                    }
                                    executorService.submit(new HttpCmdExecutor(context, socket));
                                } catch (Throwable t){
                                    log.error("Accept error ", t);
                                    try {
                                        Thread.sleep(1000); // 1s
                                    } catch (InterruptedException ignored) {
                                    }
                                }
                            }
                        }
                    }
            );
        }
        thread.start();
        log.info("HttpCmdAcceptor start successed");
    }

    public void stop(){
        try{
            if (start.compareAndSet(true, false)){
                executorService.shutdown();
                log.info("HttpCmdAcceptor stop succeed");
            }
        }catch (Throwable t){
            log.error("HttpCmdAcceptor stop error", t);
        }
    }

    private boolean isStarted(){
        return start.get();
    }
}










