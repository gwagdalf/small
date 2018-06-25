package com.small.domain.base;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.lang.Nullable;

@Data
public class BaseModel implements Serializable {

    @Column(nullable = false, name="INS_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name="UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date lastModified;

    @Column(name = "INS_OPRT", updatable = false)
    @CreatedBy
    private String createdBy;

    @Nullable
    @Column(name = "UPD_OPRT")
    @LastModifiedBy
    private String lastModifiedBy;

    public BaseModel() {
    }
}