package org.agora.statistic.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequestDto {

    private Integer userType;
    private String login;
    private String password;

    private String name;
    private String lastName;
    private String birthDate;

    private String title;
    private String specialization;
    private String dateOfFoundation;

}
