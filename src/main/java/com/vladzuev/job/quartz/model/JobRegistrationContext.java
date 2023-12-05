package com.vladzuev.job.quartz.model;

import com.vladzuev.job.crud.dto.User;
import lombok.Value;

@Value
public class JobRegistrationContext {
    User user;

}
