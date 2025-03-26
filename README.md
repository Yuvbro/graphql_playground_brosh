# graphql_playground_brosh

A personal GraphQL project built with **Spring Boot** and **GraphQL Java**, inspired by the course  
📘 _"Master GraphQL with Spring Boot Java and Testing"_

This project demonstrates **schema-first GraphQL development** using `graphql-java`, including custom object types, query resolvers, and relational mapping via JPA.

---

## 🚀 Features

- **Schema-first GraphQL API** with `.graphqls` definitions
- Embedded **H2 database** with Spring Data JPA
- Developer tools: GraphiQL, Voyager UI, and H2 Console
- Sample schema with real-world objects: `Seller`, `Gig`, `Review`, `Message`
- Minimal Spring Boot configuration, easily extendable

🧪 Developer Tools
Tool	URL	Description
GraphiQL UI	http://localhost:8080/graphiql	Interactive GraphQL query editor
Voyager UI	http://localhost:8080/voyager	GraphQL schema explorer (visual graph)
H2 Console	http://localhost:8080/h2-console/	Embedded database browser (username: sa)

Make sure spring.h2.console.enabled=true is set in application.properties.

Query Example:
```
query {
  sellers {
    id
    name
    gigs {
      title
    }
  }
}
```

## 🗺️ Schema Diagram

Here’s a visual representation of the GraphQL schema and its relationships, generated with [Voyager](https://github.com/APIs-guru/graphql-voyager):

![GraphQL Schema Diagram](schema_diagram_voyager_graphql.png)

🧑‍💻 Author
Yuval Brosh
