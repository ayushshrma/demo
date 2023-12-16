package com.hospital.management.demo.entity.token;

import com.hospital.management.demo.entity.HospitalStaff;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Data
@SQLDelete(sql = "update forgot_password_token set is_deleted = 1 where token = ?")
@Where(clause = "is_deleted = 0")
public class ForgotPasswordToken {
    @Id
    private String token;


    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private HospitalStaff user;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date issuedAt;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiresAt;

    @Column(name="is_deleted",nullable=false)
    private Boolean isDeleted;

    @PrePersist
    public void setFlags(){
        if(isDeleted==null){
            isDeleted=false;
        }
    }


}
