package com.social.graphqlsdl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity; //javax.persistence is JPA (Java Persistence API) standard
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "seller")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id // 	Marks the primary key field
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "seller")
    private Set<Gig> gigs;

    @OneToMany(mappedBy = "seller")
    private Set<Review> reviews;
}
