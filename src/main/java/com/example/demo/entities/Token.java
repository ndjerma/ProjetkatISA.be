package com.example.demo.entities;


import com.example.demo.enums.TokenTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, columnDefinition = "LONGTEXT")
    private String token;
    @Column(unique = true, name = "refresh_token", columnDefinition = "LONGTEXT")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private TokenTypeEnum tokenType = TokenTypeEnum.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
