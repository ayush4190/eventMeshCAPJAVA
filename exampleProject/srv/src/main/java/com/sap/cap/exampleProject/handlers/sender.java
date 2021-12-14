package com.sap.cap.exampleProject.handlers;

import java.util.HashMap;
import java.util.Map;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.messaging.MessagingService;
import com.sap.cds.services.messaging.TopicMessageEventContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@ServiceName("Sender")
public class sender implements EventHandler {



private Map<Object, Map<String,Object>> products =  new HashMap<>();

@Autowired
@Qualifier("messaging")
MessagingService messagingService;



@On(event = CdsService.EVENT_CREATE, entity = "Sender.Products")
public void onCreate(CdsCreateEventContext cdsCreateEventContext){
    cdsCreateEventContext.getCqn().entries().forEach(e -> products.put(e.get("ID"), e));
    cdsCreateEventContext.setResult(cdsCreateEventContext.getCqn().entries());

    TopicMessageEventContext context = TopicMessageEventContext.create("mycom/sender/queue1/demo");
context.setData("raw message payload");
messagingService.emit(context);

   





}




    
}
