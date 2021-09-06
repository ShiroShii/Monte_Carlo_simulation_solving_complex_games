package com.diplomski.backend.contract;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerBoxPlot {
	List<CategoryContract> health;
	List<CategoryContract> damageTaken;
	List<CategoryContract> damageDealt;
}
