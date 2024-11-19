package com.superalice;


import com.superalice.cli.CliService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
        String hostAddress = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.isSiteLocalAddress()) {
                        hostAddress = addr.getHostAddress();
                        System.out.println("Local IP Address: " + addr.getHostAddress());
                    }
                }
            }
            CliService.parseArguments(args, hostAddress);
        } catch (Exception e) {
            System.err.println("Error fetching local IP: " + e.getMessage());
        }
    }

}