package ir.minishopping.minishopping.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    private String id;
    private long createdDate;
    private Boolean enable;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.createdDate = Instant.now().getEpochSecond();// time in second since 1970 jan 1.
    }

}
