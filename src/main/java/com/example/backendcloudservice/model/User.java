package com.example.backendcloudservice.model;

/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "myusers")*/
public class User {
   /* @Id
    @Column(name = "id")*/
    private Long id;
   // @Column(name = "login")
    private String login;
   // @Column(name = "password")
    private String password;
   // @Column(name = "auth-token")
    private String authToken;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthToken() {
        return authToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
