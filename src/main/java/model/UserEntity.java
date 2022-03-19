package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "sdassignment1", catalog = "")
public class UserEntity {
    private int iduser;
    private String username;
    private String password;
    private String role;
    private String email;

    public UserEntity(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public UserEntity(int iduser, String username, String password, String role, String email) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public UserEntity() {

    }

    @Id
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return iduser == that.iduser &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, username, password);
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
