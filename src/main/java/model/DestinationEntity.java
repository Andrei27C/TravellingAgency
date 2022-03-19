package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "destination", schema = "sdassignment1")
public class DestinationEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int iddestination;

    private String name;

    public DestinationEntity() {
    }

    public DestinationEntity(String name) {
        this.name = name;
    }

    public DestinationEntity(int destinationId, String destinationName) {
        this.iddestination = destinationId;
        this.name = destinationName;
    }

    @Id
    @Column(name = "iddestination")
    public int getIddestination() {
        return iddestination;
    }

    public void setIddestination(int iddestination) {
        this.iddestination = iddestination;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationEntity that = (DestinationEntity) o;
        return iddestination == that.iddestination &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddestination, name);
    }
}
