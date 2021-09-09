package com.diplomski.backend.dal;

import java.util.UUID;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.Type;

import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PlayerCharacterState")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCharacterStateDbModel {
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
	private int currentHp;

	@ManyToOne
	@JoinColumn(name = "PlayerCharacterId", nullable = false)
	private PlayerCharacterDbModel playerCharacter;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlayStyle playStyle;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TargetingStyle targetingStyle;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NodeTileId", nullable = false)
	private NodeTileDbModel nodeTile;
}
