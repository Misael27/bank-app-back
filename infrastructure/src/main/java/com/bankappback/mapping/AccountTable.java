package com.bankappback.mapping;


import java.util.List;

import com.bankappback.model.EAccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString(of = "id")
@Entity
@Table(name = "Accounts", schema = "public")
public class AccountTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "number", nullable = false)
	String number;
    
    @Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private EAccountType type;
    
    @Column(name = "init_balance")
    private Double initBalance;
    
    @Column(name = "state", nullable = false)
    private Boolean state;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="client_id", nullable=false, updatable=false)
    private ClientTable client;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
    private List<MovementTable> movements;
    
}