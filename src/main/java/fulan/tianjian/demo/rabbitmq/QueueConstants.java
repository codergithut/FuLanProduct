package fulan.tianjian.demo.rabbitmq;

//参考:https://www.cnblogs.com/hhhshct/p/9718063.html
public class QueueConstants {
	

    // 消息交换
    public static final String MESSAGE_EXCHANGE_REMOTE = "message.direct.remoteexchange";
    // 消息队列名称
    public static final String MESSAGE_QUEUE_NAME_REMOTE = "message.remotequeue";
    // 消息路由键
    public static final String MESSAGE_ROUTE_KEY_REMOTE = "message.remoteroute";
    
    // 消息交换
    public static final String MESSAGE_EXCHANGE_RECORD = "message.direct.recordexchange";
    // 消息队列名称
    public static final String MESSAGE_QUEUE_NAME_RECORD = "message.recordqueue";
    // 消息路由键
    public static final String MESSAGE_ROUTE_KEY_RECORD = "message.recordroute";
    
    public static final String MESSAGE_EXCHANGE_DL = "message.direct.exchange.dl";
    
    public static final String MESSAGE_QUEUE_NAME_DL = "message.direct.dl";
    
    public static final String MESSAGE_ROUTE_KEY_DL = "message.route.dl";
    
    
    public static final String MESSAGE_EXCHANGE_DELAYED = "message.exchange.delayed";
    
    public static final String MESSAGE_QUEUE_NAME_DELAYED = "message.direct.delayed";
    
    public static final String MESSAGE_ROUTE_KEY_DELAYED = "message.route.delayed";

}
