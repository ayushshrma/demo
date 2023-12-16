package com.hospital.management.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="RoleSeqGen",sequenceName = "RoleSeq",initialValue = 1,allocationSize = 1)
    public Long role_id;

    @Column(name="authority",unique = true,updatable = false)
    @NotBlank
    public String authority;


    @Column(name="is_deleted",nullable = false,columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public Role() {
    }

    @PrePersist
    public void setFlags(){
        if(isDeleted==null){
            isDeleted=false;
        }
    }

    public Role(Long role_id, String authority, Boolean isDeleted) {
        this.role_id = role_id;
        this.authority = authority;
        this.isDeleted = isDeleted;
    }

    public Long getRole_id() {
        return role_id;
    }

    public Role setRole_id(Long role_id) {
        this.role_id = role_id;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public Role setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Role setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}