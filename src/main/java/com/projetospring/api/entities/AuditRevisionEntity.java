package com.projetospring.api.entities;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "revinfo")
@RevisionEntity(AuditRevisionListener.class)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AuditRevisionEntity extends DefaultRevisionEntity {

    @Column(name = "username", length = 150)
    private String username;
}
