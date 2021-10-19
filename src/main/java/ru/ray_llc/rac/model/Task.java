package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks", uniqueConstraints = {@UniqueConstraint(columnNames = {"number_auto",
    "address"}, name = "tasks_unique_number_auto_address_idx")})
public class Task extends AbstractNamedEntity {


  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "address", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String address;

  @Column(name = "phone", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String phone;

  @Column(name = "number_auto", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String number_auto;

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
  @NotNull
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Date registered = new Date();


  public Task() {
  }

  public Task(Double longitude, Double latitude, String address, String phone, String number_auto) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.address = address;
    this.phone = phone;
    this.number_auto = number_auto;
    this.name = "инциндент";
  }

  public Task(Double longitude, Double latitude, String address, String phone, String number_auto, Integer id) {
    super.setId(id);
    this.longitude = longitude;
    this.latitude = latitude;
    this.address = address;
    this.phone = phone;
    this.number_auto = number_auto;
    this.name = "инциндент";
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", address=" + address +
        ", phone=" + phone +
        ", number_auto=" + number_auto +
        ", longitude=" + longitude.toString() +
        ", latitude=" + latitude.toString() +
        ", enabled=" + enabled +
        ", name=" + name +
        '}';
  }
}
