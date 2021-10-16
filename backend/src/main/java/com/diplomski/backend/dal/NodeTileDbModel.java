package com.diplomski.backend.dal;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.diplomski.common.board.TerrainFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NodeTile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeTileDbModel {
	@Id
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TerrainFeature terrainFeature;

	private int x;

	private int y;

	@ManyToMany
	@JoinTable(name = "NodeBranches", joinColumns = @JoinColumn(name = "InitialNodeId"), inverseJoinColumns = @JoinColumn(name = "TargetNodeId"))
	private List<NodeTileDbModel> reachableNodes;

	@ManyToMany(mappedBy = "reachableNodes")
	private List<NodeTileDbModel> reachableBy;

	@ManyToOne
	@JoinColumn(name = "BattleId")
	private BattleDbModel battle;

	@OneToMany(mappedBy = "nodeTile", cascade = CascadeType.ALL)
	private List<PlayerCharacterStateDbModel> characterStates;

	@OneToMany(mappedBy = "nodeTile", cascade = CascadeType.ALL)
	private List<MonsterStateDbModel> monsterStates;
}
