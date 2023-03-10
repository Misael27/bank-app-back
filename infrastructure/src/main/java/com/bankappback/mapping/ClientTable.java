package com.bankappback.mapping;


import lombok.*;

import java.util.Date;
import java.util.List;

import com.bankappback.model.EGenger;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"password", "birthdate"})
@ToString(of = "id")
@Entity
@Table(name = "Clients", schema = "public")
public class ClientTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "name", length = 100, nullable = false)
	private String name;
    
    @Column(name = "gender", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private EGenger gender;
    
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    @Column(name = "person_id", unique = true, nullable = false)
	private String personId;
	
    @Column(name = "address", nullable = false)
	private String address;
	
    @Column(name = "phone", length = 25, nullable = false)
	private String phone;

    @Column(name = "password", nullable = false)
	private String password	;
    
    @Column(name = "state", nullable = false)
    private Boolean state;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
    private List<AccountTable> accounts;
    
}
