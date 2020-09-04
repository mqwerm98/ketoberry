package com.ketoberry.infra.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Emailmessage {

    private String to;

    private String subject;

    private String message;

}
