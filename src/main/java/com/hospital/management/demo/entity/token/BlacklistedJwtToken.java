package com.hospital.management.demo.entity.token;

import com.hospital.management.demo.entity.HospitalStaff;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name="blacklisted_jwt_tokens")
@SQLDelete(sql = "update blacklisted_jwt_tokens set is_deleted = 1 where token = ?")
@Where(clause = "is_deleted = 0")
@Data
public class BlacklistedJwtToken {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private HospitalStaff user;

    @Column(name="is_deleted",nullable = false,columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public  BlacklistedJwtToken(){
    }
    public BlacklistedJwtToken(AccessToken token) {
        this.token = token.getToken();
        this.user = token.getUser();
    }
    public BlacklistedJwtToken(RefreshToken token) {
        this.token = token.getToken();
        this.user = token.getUser();
    }

    @PrePersist
    public void setFlags(){
        if(isDeleted==null){
            isDeleted=false;
        }
    }
}
