package com.bankappback.mapping;

import java.util.Date;

import com.bankappback.model.EMovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "Movements", schema = "public")
public class MovementTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private EMovementType type;
    
    @Column(name = "value")
    private Double value;
    
    @Column(name = "balance")
    private Double balance;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="account_id", nullable=false, updatable=false)
    private AccountTable account;
    
}