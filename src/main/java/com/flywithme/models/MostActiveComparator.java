package com.flywithme.models;

import java.util.Comparator;

public class MostActiveComparator implements Comparator<MostActiveModel> {

	public int compare(MostActiveModel o1, MostActiveModel o2) {
		return Integer.compare(o1.getWorkingDays(), o2.getWorkingDays());
	}

}
