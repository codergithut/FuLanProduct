package fulan.tianjian.demo.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

	public <T> void sendMessage(String exChangeName, String exRouteKey, T sendMessage) {
		rabbitTemplate.convertAndSend(exChangeName, exRouteKey, sendMessage);
		
	}
	
	
	public <T> void sendMessage(String exChangeName, String exRouteKey, T sendMessage, Long delayTime) {
		MessagePostProcessor messagePost = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties = message.getMessageProperties();
                properties.setExpiration(delayTime + "");
                return message;
            }
        };
		rabbitTemplate.convertAndSend(exChangeName, exRouteKey, sendMessage, messagePost);
	}
	
	public <T> void sendDelayMessage(T sendMessage, Long delayTime) {
		sendMessage(QueueConstants.MESSAGE_EXCHANGE_DELAYED, QueueConstants.MESSAGE_ROUTE_KEY_DELAYED, sendMessage, delayTime);
		
	}
	

}