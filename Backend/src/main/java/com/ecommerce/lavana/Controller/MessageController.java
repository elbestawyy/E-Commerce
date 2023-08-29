package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.DTO.AdminMessageRequestDTO;
import com.ecommerce.lavana.Entity.Message;
import com.ecommerce.lavana.service.MessageService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@CrossOrigin("http://localhost:4200")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/secure/add")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
                            @RequestBody Message messageRequest){
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        messageService.postMessage(user,messageRequest);
    }

    @PutMapping("/secure/admin")
    public void putMessage(@RequestHeader(value = "Authorization") String token,
                           @RequestBody AdminMessageRequestDTO adminMessageRequestDTO)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration Only");
        }
        messageService.putMessage(user,adminMessageRequestDTO);
    }
}
