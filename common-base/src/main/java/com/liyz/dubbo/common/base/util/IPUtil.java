package com.liyz.dubbo.common.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/11 10:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IPUtil {

    private static final char SEPARATOR = '.';


    private static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     *
     * @return String
     */
    public static String getLocalIP() {
        InetAddress inetAddr = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                inetAddr = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean hasFound = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (hasFound) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        inetAddr = (InetAddress) ips.nextElement();
                        if (inetAddr.isSiteLocalAddress()
                                && !inetAddr.isLoopbackAddress() // 127.开头的都是lookback地址
                                && inetAddr.getHostAddress().indexOf(':') == -1) {
                            hasFound = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Objects.requireNonNull(inetAddr, "can not get the local site inetaddress.");
        return inetAddr.getHostAddress();
    }
}
