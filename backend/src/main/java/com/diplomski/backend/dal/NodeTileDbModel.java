package com.diplomski.backend.dal;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "NodeTile")
@Data
public class NodeTileDbModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	@ManyToMany
	@JoinTable(name="NodeBranches",
	 joinColumns=@JoinColumn(name="InitialNodeId"),
	 inverseJoinColumns=@JoinColumn(name="TargetNodeId")
	)
	private List<NodeTileDbModel> reachableNodes;

	@ManyToMany
	@JoinTable(name="NodeBranches",
	 joinColumns=@JoinColumn(name="TargetNodeId"),
	 inverseJoinColumns=@JoinColumn(name="InitialNodeId")
	)
	private List<NodeTileDbModel> reachableBy;

	@ManyToOne
	@JoinColumn(name = "NodeBoardId", nullable = false)
	private NodeBoardDbModel nodeBoard;
	
	@OneToMany(mappedBy = "nodeTile")
	private List<CharacterStateDbModel> characterStates;
}
