package model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vacationpackage", schema = "sdassignment1")
public class VacationpackageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int idvacationpackage;

    private String name;
    private Integer price;
    private Date periodstart;
    private Date periodend;
    private String extraDetails;
    private Integer noofpeoplethatcanbook;
    private String status;
    private int destinationId;

    @Basic
    @Column(name = "destinationid")
    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    @Id
    @Column(name = "idvacationpackage")
    public int getIdvacationpackage() {
        return idvacationpackage;
    }

    public void setIdvacationpackage(int idvacationpackage) {
        this.idvacationpackage = idvacationpackage;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "periodstart")
    public Date getPeriodstart() {
        return periodstart;
    }

    public void setPeriodstart(Date periodstart) {
        this.periodstart = periodstart;
    }

    @Basic
    @Column(name = "periodend")
    public Date getPeriodend() {
        return periodend;
    }

    public void setPeriodend(Date periodend) {
        this.periodend = periodend;
    }

    @Basic
    @Column(name = "extraDetails")
    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    @Basic
    @Column(name = "noofpeoplethatcanbook")
    public Integer getNoofpeoplethatcanbook() {
        return noofpeoplethatcanbook;
    }

    public void setNoofpeoplethatcanbook(Integer noofpeoplethatcanbook) {
        this.noofpeoplethatcanbook = noofpeoplethatcanbook;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationpackageEntity that = (VacationpackageEntity) o;
        return idvacationpackage == that.idvacationpackage &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(periodstart, that.periodstart) &&
                Objects.equals(periodend, that.periodend) &&
                Objects.equals(extraDetails, that.extraDetails) &&
                Objects.equals(noofpeoplethatcanbook, that.noofpeoplethatcanbook) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvacationpackage, name, price, periodstart, periodend, extraDetails, noofpeoplethatcanbook, status);
    }

    @Override
    public String toString() {
        return "VacationpackageEntity{" +
                "idvacationpackage=" + idvacationpackage +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", periodstart=" + periodstart +
                ", periodend=" + periodend +
                ", extraDetails='" + extraDetails + '\'' +
                ", noofpeoplethatcanbook=" + noofpeoplethatcanbook +
                ", status='" + status + '\'' +
                ", destinationId=" + destinationId +
                '}';
    }


}
