package com.diplomski.common.activity.action.attack;

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
public class AttackActionActivity extends Activity {
	private UUID targetId;
	private int damage;
}
