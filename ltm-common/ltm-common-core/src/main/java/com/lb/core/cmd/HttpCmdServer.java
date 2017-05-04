package com.lb.core.cmd;

import com.lb.core.exception.HttpCmdException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HttpCmdServer 主要用于curl
 * Created by libo on 2017/5/4.
 */
@Slf4j
public class HttpCmdServer {

    private final AtomicBoolean start = new AtomicBoolean(false);

    private HttpCmdAcceptor acceptor;

    @Getter
    private int port;
    private String bindAddr;
    private HttpCmdContext context;
    private int portFindTimes;

    private HttpCmdServer(int port, String bindAddr) {
        this.port = port > 0 ? port : 8719;
        this.bindAddr = bindAddr;
        this.context = new HttpCmdContext();
    }

    public void start() throws HttpCmdException {
        try {
            if (start.compareAndSet(false, true)) {
                acceptor = new HttpCmdAcceptor(getServerSocket(), context);
                acceptor.start();
                log.info("\n=====================================================================================\n" +
                        "Start succeed at port: {} \n" +
                        "=====================================================================================\n");
            }
        } catch (Exception e) {
            log.error("Start error at port{}", port, e);
            throw new HttpCmdException(e);
        }
    }

    public void stop() {
        if (acceptor != null)
            acceptor.stop();
    }

    private void registerCommand(HttpCmdProc proc) {
        context.addCmdProcessor(proc);
    }

    public void registerCommands(HttpCmdProc... procs) {
        if (procs != null && procs.length > 0) {
            for (HttpCmdProc proc : procs) {
                this.registerCommand(proc);
            }
        }
    }


    private ServerSocket getServerSocket() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 100);
            serverSocket.setReuseAddress(true);
        } catch (BindException e) {
            port = port + 1;
            serverSocket = getServerSocket();
            if (portFindTimes++ > 50) {
                throw e;
            }
        }
        return serverSocket;
    }


    /**
     * 保证同一个jvm下只有一个HttpCmdServer
     */
    public static class Factory {
        private static HttpCmdServer httpCmdServer;

        public static HttpCmdServer getHttpCmdServer(String bindAddr, int port) {
            if (httpCmdServer != null)
                return httpCmdServer;
            synchronized (Factory.class) {
                if (httpCmdServer != null)
                    return httpCmdServer;
                httpCmdServer = new HttpCmdServer(port, bindAddr);
                return httpCmdServer;
            }
        }
    }
}
