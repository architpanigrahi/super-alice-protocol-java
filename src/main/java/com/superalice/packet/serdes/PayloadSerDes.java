package com.superalice.packet.serdes;

public interface PayloadSerDes<T> {

    byte[] serialize(T payload);

    T deserialize(byte[] bytes);

}
