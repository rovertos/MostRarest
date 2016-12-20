package mrc.config;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Global {
	
	// TEST POPULATIONS
	
	public static int RUN_TEST_STEPS = 100;
	
	public static int THE_WILD_CARRYING_CAPACITY_FACTOR = 1;
	
	public static String[] NEW_SPAWN_STATUSES = 	{"3",	"5",	"4",	"3",	"1"};
	
	public static String[] GROWTH_THRESHOLDS = 		{"50",	"50",	"60",	"70",	"100"};
	
	// ...
	
	public static String[][] INSTABILITIES_IN_THE_WILD = {};
	//public static String[][] INSTABILITIES_IN_THE_WILD = {{"c0","2"},{"c2","4"},{"b0","2"}};
	//public static String[][] INSTABILITIES_IN_THE_WILD = {{"c0","2"},{"c2","4"},{"b0","2"},{"b1","4"}};
	//public static String[][] INSTABILITIES_IN_THE_WILD = {{"c0","2"},{"c1","2"},{"c2","4"},{"b0","2"},{"b1","4"}};
	
	public static String[] AREAS_LVL_0 = {"1","3","5"};
	
	public static String[] AREAS_LVL_1 = {"0","1","2","3","4","5","6"};
	
	public static String[] AREAS_LVL_2 = {"0,0,0","1","2,2,2","3","4,4,4","5","6,6,6"};
	
	public static String[] AREAS_LVL_3 = {"0","1","2","3","4","5","6"};
	
	public static String[] AREAS_LVL_4 = {"1","5"};
	
	public static int NUMBER_OF_ROBOTS = 0;
	
	// TEST CONGIG DATA <END>
	
	public static String[] LEVELS = {"a","b","c","d","e"};
	
	public static String[] POPS_PER_LEVEL = {"4","7","15","7","2"};
	
	public static NumberFormat formatter;
	
	static {
		
		formatter = NumberFormat.getInstance(Locale.US);
		
		formatter.setMaximumFractionDigits(2);
		
		formatter.setMinimumFractionDigits(1);
		
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		
	}
	
}
