package com.diplomski.backend.dal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "BoardState")
@Data
public class BoardStateDbModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "boardState")
	private List<CharacterStateDbModel> characterStates;

	@ManyToOne
	@JoinColumn(name = "NodeBoardId", nullable = false)
	private NodeBoardDbModel nodeBoard;
}
