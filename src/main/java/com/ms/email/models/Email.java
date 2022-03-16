package com.ms.email.models;

import com.ms.email.enums.StatusEmail;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="TB_EMAIL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Email implements Serializable {
    public static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    //confia no pai que mais na frente tu vai ver
    @Column(nullable = false)
    private String ownerRef;

    @Column(nullable = false)
    private String emailFrom;

    @Column(nullable = false)
    private String emailTo;

    //titulo
    @Column(nullable = false)
    private String subject;

    //insere mais de 255 caracteres, já que o limite comum é apenas 255
    @Column(columnDefinition = "Text",nullable = false)
    private String text;

    private LocalDateTime sendDateEmail;

    //sent ou error (enum)
    private StatusEmail statusEmail;




}
