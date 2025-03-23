package com.social.graphqlsdl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

// H2 is an open-source lightweight Java database - open source in memory database (not persisted to disk) for small-medium size applications.
// There is an option to File-based Database - where data is persisted to disk in a local file
//http://localhost:8080/h2-console/login.do?jsessionid=cfa2e2cdc2c494085d799abd9eae2699

@Entity
@Table(name = "gig")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    private String title;
    private String description;
    private String category;

    @ManyToOne()
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "gig")
    private Set<Review> reviews;

}
