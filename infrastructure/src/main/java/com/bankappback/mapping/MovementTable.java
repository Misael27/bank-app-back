package com.bankappback.mapping;

import java.util.Date;

import com.bankappback.model.EAccountType;
import com.bankappback.model.EMovementType;
import com.bankappback.model.MovementReport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NamedNativeQuery(
	    name = "find_movement_report",
	    query =
	    		"SELECT m.id as id, m.date as date, c.name as name, a.number as number, a.type as type, a.state as state, "
		 		+ "CASE "
		 		+ "	WHEN m.type = 'Retiro' THEN m.balance + m.value "
		 		+ "	ELSE m.balance - m.value "
		 		+ "END initbalance, "
		 		+ "CASE  "
		 		+ "	WHEN m.type = 'Retiro' THEN m.value * -1 "
		 		+ "	ELSE m.value "
		 		+ "END movement, "
		 		+ "m.balance as balance "
		 		+ "FROM public.movements m "
		 		+ "JOIN public.accounts a ON a.id = m.account_id "
		 		+ "JOIN public.clients c ON c.id = a.client_id "
		 		+ "WHERE DATE(m.date) >= DATE(:sdate) AND DATE(m.date) <= DATE(:edate) AND c.id = :cid",
	    resultSetMapping = "movement_report"
)
@SqlResultSetMapping(
    name = "movement_report",
    classes = @ConstructorResult(
        targetClass = MovementReport.class,
        columns = {
            @ColumnResult(name = "id", type = Integer.class),
            @ColumnResult(name = "date", type = Date.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "number", type = String.class),
            @ColumnResult(name = "type", type = EAccountType.class),
            @ColumnResult(name = "state", type = Boolean.class),
            @ColumnResult(name = "initbalance", type = Double.class),
            @ColumnResult(name = "movement", type = Double.class),
            @ColumnResult(name = "balance", type = Double.class)
        }
    )
)
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