package nurdanemin.userservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int apartmentNumber;
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;

    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

}
