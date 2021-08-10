package com.diplomski.backend.dal;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.resource.Weapon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PlayerCharacter")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCharacterDbModel {
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
	
	@Column(nullable = false)
	private int dexterity;
	
	@Column(nullable = false)
	private int strength;
	
	@Column(nullable = false)
	private int walkingSpeed;
	
	@Column(nullable = false)
	private int armorClass;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CharacterClass characterClass;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@ElementCollection(targetClass = Weapon.class)
	private List<Weapon> weapons;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CharacterLevel characterLevel;

	@OneToMany(mappedBy = "playerCharacter")
	private List<PlayerCharacterStateDbModel> playerCharacterStates;
}
