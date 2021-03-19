package com.flashcard.demo.rest;

import com.flashcard.demo.registration.RegistrationReq;
import com.flashcard.demo.registration.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/registration")
public class RegistrationRESTController {

    private RegistrationService registrationService;

    public RegistrationRESTController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/")
    public String register(@RequestBody RegistrationReq req) {
        return registrationService.register(req);
    }

}
