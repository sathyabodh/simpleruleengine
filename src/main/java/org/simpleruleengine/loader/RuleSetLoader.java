package org.simpleruleengine.loader;

import java.io.File;
import java.util.List;

import org.simpleruleengine.ruleset.RuleSet;

public interface RuleSetLoader {

	List<RuleSet> load(File file);
}
