
package com.bernardomg.validation.test.architecture.test;

import com.bernardomg.validation.test.architecture.rule.CodingRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;

@AnalyzeClasses(packages = "com.bernardomg.validation", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureRulesTest {

    @ArchTest
    static final ArchTests codingRules = ArchTests.in(CodingRules.class);

}
