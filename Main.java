package assignment5;

import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static String myPackage; // package of Critter file. Critter cannot be in default pkg.
	private static List<String> critter_list;
	
	private static String pkgLoc;
	
	// Gets the package name. The usage assumes that Critter and its subclasses are
	// all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
		pkgLoc = Paths.get("").toAbsolutePath().toString().replace("\\", "\\\\") + "\\\\" + myPackage;
		critter_list = makeCritterList();
	}

	public static void main(String[] args) {
		// launch(args);
		for(String s : critter_list) {
			System.out.println(s);
		}
	}
	
	public static List<String> makeCritterList() {
		File f = new File(pkgLoc);
		String[] subFiles = f.list();
		List<String> findClasses = new ArrayList<String>();
		List<String> findCritters = new ArrayList<String>();
		for (String s : subFiles) {
			if (s.endsWith(".class")) {
				findClasses.add(s);
			}
		}
		for (String s : findClasses) {
			String critName = s.substring(0, s.indexOf(".class"));
			try {
				Class<?> check_class = Class.forName(myPackage + '.' + critName);
				int mod = check_class.getModifiers();
				if(Modifier.isAbstract(mod) || Modifier.isPrivate(mod)) {
					continue;
				}
				Class<?> critter_class = Critter.class;
				if (critter_class.isAssignableFrom(check_class)) {
					findCritters.add(critName);
					//System.out.println(critName);
				}
			} catch (ClassNotFoundException e) {
				//Shouldn't happen
				continue;
			}
		}
		return findCritters;
	}
}
