package com.diplomski.common.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WalkMovementActivity extends Activity {
	private int initialPositionX;
	private int initialPositionY;
	private int targetPositionX;
	private int targetPositionY;
}
