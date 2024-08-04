package com.aomerge.userservices.services.serviceDTO;

import com.aomerge.userservices.config.validation.access.BaseAccessDTO;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.models.Access;

public interface AccessDTO {
    public Access getAll();
    public Access save(HeaderValidationDTO token, BaseAccessDTO access);
    public Access update(HeaderValidationDTO token, BaseAccessDTO access);
    public void delete(HeaderValidationDTO token, String id);
}
