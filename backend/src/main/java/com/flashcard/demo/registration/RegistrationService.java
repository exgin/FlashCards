package com.flashcard.demo.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public String register(RegistrationReq req) {
        return "REG SERVICE WAS HIT!";
    }

}
