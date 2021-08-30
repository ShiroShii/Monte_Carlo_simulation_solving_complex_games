package com.diplomski.backend.contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NameValueIntPair {
	private String name;
	private int value;
}
