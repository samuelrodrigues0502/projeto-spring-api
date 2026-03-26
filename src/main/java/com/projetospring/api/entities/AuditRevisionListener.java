package com.projetospring.api.entities;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity revision = (AuditRevisionEntity) revisionEntity;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "system";
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }

        revision.setUsername(username);
    }
}
