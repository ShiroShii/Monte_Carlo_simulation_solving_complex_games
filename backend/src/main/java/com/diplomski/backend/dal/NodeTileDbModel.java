package com.diplomski.backend.dal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "NodeTile")
@Data
public class NodeTileDbModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
