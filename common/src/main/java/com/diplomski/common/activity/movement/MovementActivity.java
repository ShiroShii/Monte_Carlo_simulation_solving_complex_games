package com.diplomski.common.activity.movement;

import java.util.UUID;

import com.diplomski.common.activity.Activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovementActivity extends Activity {
	private UUID initialTileId;
	private UUID finalTileId;
}
