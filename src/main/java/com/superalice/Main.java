package com.superalice;


import com.superalice.cli.CliService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
        CliService.parseArguments(args);
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.isSiteLocalAddress()) {
                        System.out.println("Local IP Address: " + addr.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching local IP: " + e.getMessage());
        }
    }

}