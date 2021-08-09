package com.diplomski.backend.dal;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BoardState")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardStateDbModel {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "boardState")
	private List<PlayerCharacterStateDbModel> characterStates;

	@ManyToOne
	@JoinColumn(name = "NodeBoardId", nullable = false)
	private NodeBoardDbModel nodeBoard;
}
