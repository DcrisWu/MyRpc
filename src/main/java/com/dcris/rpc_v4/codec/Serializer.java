package com.dcris.rpc_v4.codec;

public interface Serializer {

    // 将对象序列化为字节数组
    byte[] serialize(Object obj);

    // 将字节数组反序列化为消息，使用java自带序列化方式不用messageType也能得到相应的对象(序列化字节数组里包含类信息)
    // 其他方式需指定消息格式，再根据message转化为相应的对象
    Object deserialize(byte[] bytes,int messageType);

    // 返回使用的序列器
    // 0: java自带序列化方式, 1: json序列化方式
    int getType();

    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
