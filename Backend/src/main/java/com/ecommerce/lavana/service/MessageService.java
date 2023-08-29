package com.ecommerce.lavana.service;

import com.ecommerce.lavana.DTO.AdminMessageRequestDTO;
import com.ecommerce.lavana.Entity.Message;

public interface MessageService {
    public void postMessage(String user , Message messageRequest);
    public void putMessage(String admin , AdminMessageRequestDTO adminMessageRequestDTO) throws Exception;
}
