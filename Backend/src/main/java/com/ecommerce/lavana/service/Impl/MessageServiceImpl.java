package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.MessageRepository;
import com.ecommerce.lavana.DTO.AdminMessageRequestDTO;
import com.ecommerce.lavana.Entity.Message;
import com.ecommerce.lavana.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void postMessage(String user , Message messageRequest){
        Message message = new Message(messageRequest.getTitle(),messageRequest.getQuestion());
         message.setUser(user);
         messageRepository.save(message);
    }

    @Override
    public void putMessage(String admin , AdminMessageRequestDTO adminMessageRequestDTO) throws Exception{
        Optional<Message> message = messageRepository.findById(adminMessageRequestDTO.getId());
        if (!message.isPresent()){
            throw new Exception("Message not found");
        }
        message.get().setAdmin(admin);
        message.get().setResponse(adminMessageRequestDTO.getResponse());
        message.get().setClosed(true);

        messageRepository.save(message.get());
    }
}
