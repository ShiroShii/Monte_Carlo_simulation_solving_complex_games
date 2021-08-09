package com.diplomski.common.activity;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WalkMovementActivity extends Activity {
	private UUID initialTileId;
	private UUID finalTileId;
}
