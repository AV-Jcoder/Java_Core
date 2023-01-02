package com.afoninav.javacore.chapter22;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Пример применения класса InetAddress
 *
 */

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);

        address = InetAddress.getByName("www.google.com");
        System.out.println(address);

        InetAddress[] addresses = InetAddress.getAllByName("yandex.ru");
        for (InetAddress addr : addresses) {
            System.out.println(addr);
        }
    }
}
