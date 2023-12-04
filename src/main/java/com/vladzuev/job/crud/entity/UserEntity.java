package com.vladzuev.job.crud.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

import static java.util.Objects.hash;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public final boolean equals(final Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (Hibernate.getClass(this) != Hibernate.getClass(otherObject)) {
            return false;
        }
        final UserEntity other = (UserEntity) otherObject;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return hash(this.getId());
    }
}
