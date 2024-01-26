package com.eduardo.curso.models;

import antlr.collections.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = User.TABLE_NAME)

public class User {

    public interface CreateUser {}
    public interface UpdateUser {}        

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)

    //*  USERNAME/
    private Long id;
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String name;

    //* PASSWORD/
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    //private List<Task> task = new ArrayList<Task>();


    public User() {

    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
         return true;
        
         if(obj == null) 
            return false;
      
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;

        if(this.id == null)
            if(other.id != null)
                return false;
        else if(!this.id.equals(other.id)) 
            return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name)
         && Objects.equals(this.password, other.password);           
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime* result + ((this.id == null) ? 0 : this.id.hashCode()); 
        return result;
    }

}
