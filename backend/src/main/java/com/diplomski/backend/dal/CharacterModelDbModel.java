package com.diplomski.backend.dal;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;

import lombok.Data;

@Entity
@Table(name = "CharacterModel")
@Data
public class CharacterModelDbModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CharacterClass characterClass;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CharacterLevel characterLevel;

	@OneToMany(mappedBy = "characterModel")
	private List<CharacterStateDbModel> characterStates;
}
