package com.globallogic.challenge.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserData {
  @Id
  private String id;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private String token;
  private Boolean isActive;
  private String name;
  private String email;
  private String password;

  @OrderColumn(name = "phones")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private PhoneData[] phones;
}
