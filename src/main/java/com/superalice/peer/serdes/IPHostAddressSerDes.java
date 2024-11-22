package com.superalice.peer.serdes;

public class IPHostAddressSerDes {

    public static byte[] serialize(String address) {
        String[] parts = address.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid address format. Must be 'IP:Port'.");
        }
        String ip = parts[0];
        int port = Integer.parseInt(parts[1]);
        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format.");
        }
        byte[] result = new byte[6];
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(octets[i]);
            if (octet < 0 || octet > 255) {
                throw new IllegalArgumentException("IP octet out of range: " + octet);
            }
            result[i] = (byte) octet;
        }
        result[4] = (byte) (port >> 8);
        result[5] = (byte) (port & 0xFF);
        return result;
    }

    public static String deserialize(byte[] data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid data length. Must be 6 bytes.");
        }
        StringBuilder ip = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int octet = Byte.toUnsignedInt(data[i]);
            ip.append(octet);
            if (i < 3) {
                ip.append(".");
            }
        }
        int port = ((data[4] & 0xFF) << 8) | (data[5] & 0xFF);
        return ip + ":" + port;
    }

}
