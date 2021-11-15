package ru.diasoft.library.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false, unique = true)
    private long id;

    @Column(name = "author_name", nullable = false, unique = true)
    private String name;
}
