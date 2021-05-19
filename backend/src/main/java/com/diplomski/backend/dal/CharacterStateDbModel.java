package com.diplomski.backend.dal;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.Data;

@Entity
@Table(name = "CharacterState")
@Data
public class CharacterStateDbModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	@Column(nullable = false)
	private int currentHp;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlayStyle playStyle;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TargetingStyle targetingStyle;

	@ManyToOne
	@JoinColumn(name = "CharacterModelId", nullable = false)
	private CharacterModelDbModel characterModel;

	@ManyToOne
	@JoinColumn(name = "BoardStateId", nullable = false)
	private BoardStateDbModel boardState;
	
	@ManyToOne
	@JoinColumn(name = "NodeTileId", nullable = false)
	private NodeTileDbModel nodeTile;
}
