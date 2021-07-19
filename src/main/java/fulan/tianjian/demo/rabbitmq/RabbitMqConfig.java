package fulan.tianjian.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;


@Configuration
public class RabbitMqConfig {
 
	/**
     * 交换配置
     *
     * @return
     */
    @Bean
    public DirectExchange remoteDirectExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueConstants.MESSAGE_EXCHANGE_REMOTE)
                .durable(true)
                .build();
    }

    /**
     * 消息队列声明
     *
     * @return
     */
    @Bean
    public Queue remoteQueue() {
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_REMOTE)
                .build();
    }

    /**
     * 消息绑定
     *
     * @return
     */
    @Bean
    public Binding remoteBinding() {
        return BindingBuilder.bind(remoteQueue())
                .to(remoteDirectExchange())
                .with(QueueConstants.MESSAGE_ROUTE_KEY_REMOTE);
    }
    
    /**
     * 交换配置
     *
     * @return
     */
    @Bean
    public DirectExchange recordDirectExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueConstants.MESSAGE_EXCHANGE_RECORD)
                .durable(true)
                .build();
    }

    /**
     * 消息队列声明
     *
     * @return
     */
    @Bean
    public Queue recordQueue() {
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_RECORD)
                .build();
    }

    /**
     * 消息绑定
     *
     * @return
     */
    @Bean
    public Binding recordBinding() {
        return BindingBuilder.bind(recordQueue())
                .to(recordDirectExchange())
                .with(QueueConstants.MESSAGE_ROUTE_KEY_RECORD);
    }
    
    /**
     * 交换配置
     *
     * @return
     */
    @Bean
    public DirectExchange dlDirectExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueConstants.MESSAGE_EXCHANGE_DL)
                .durable(true)
                .build();
    }

    /**
     * 消息队列声明
     *
     * @return
     */
    @Bean
    public Queue dlQueue() {
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_DL)
                .build();
    }

    @Bean
    public Binding DlBinding() {
        return BindingBuilder.bind(dlQueue()).to(dlDirectExchange()).with(QueueConstants.MESSAGE_ROUTE_KEY_DL);
    }
    
    
    
    @Bean
    DirectExchange delayedDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueConstants.MESSAGE_EXCHANGE_DELAYED)
        		.durable(true)
                .build();
    }
    

    @Bean
    Queue delayedQueue() {
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_DELAYED)
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", QueueConstants.MESSAGE_EXCHANGE_DL)
                //会固定死不太妙
//                .withArgument("x-message-ttl", 60000)
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueConstants.MESSAGE_ROUTE_KEY_DL).build();
    }
    
    @Bean
    public Binding DelayedBinding() {
        return BindingBuilder.bind(delayedQueue()).to(delayedDirect()).with(QueueConstants.MESSAGE_ROUTE_KEY_DELAYED);
    }
    
    
    
    
    
    
	/**
	 * 消息接受转换消息体
	 * @param connectionFactory
	 * @return
	 */
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
    
    
    /**
     * 消息转换发送模板转换
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    
    
    /**
     * 自定义转换器用fastjson处理，就看看好了
     * @author 14681
     *
     */
    public static class SelfConverter extends AbstractMessageConverter {
        @Override
        protected Message createMessage(Object object, MessageProperties messageProperties) {
            messageProperties.setContentType("application/json");
            return new Message(JSON.toJSONBytes(object), messageProperties);
        }

        @Override
        public Object fromMessage(Message message) throws MessageConversionException {
            return JSON.parse(message.getBody());
        }
    }
    
    
    
  
}
