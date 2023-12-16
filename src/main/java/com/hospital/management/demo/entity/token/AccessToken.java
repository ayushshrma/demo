package com.hospital.management.demo.entity.token;

import com.hospital.management.demo.entity.HospitalStaff;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Data
@SQLDelete(sql = "update access_token set is_deleted = 1 where token = ?")
@Where(clause = "is_deleted = 0")
@Table(name="access_token")
public class AccessToken {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private HospitalStaff user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiresAt;

    @Column(name="is_deleted",nullable = false,columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist
    public void setFlags(){
        if(isDeleted==null){
            isDeleted=false;
        }
    }
}
