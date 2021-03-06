package com.bianjp.blog.entity_helper;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 100L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Convert(converter = DateTimeConverter.class)
  @LastModifiedDate
  protected DateTime updatedAt;

  @Convert(converter = DateTimeConverter.class)
  @CreatedDate
  protected DateTime createdAt;

  @Transient
  public boolean isNewRecord() {
    return id == null || id == 0;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    BaseEntity other = (BaseEntity) obj;
    if (id == null && other.id == null) {
      return super.equals(obj);
    } else {
      return Objects.equals(id, other.id);
    }
  }
}
