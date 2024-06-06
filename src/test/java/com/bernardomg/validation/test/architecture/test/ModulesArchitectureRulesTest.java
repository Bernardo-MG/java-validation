
package com.bernardomg.validation.test.architecture.test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = { "com.bernardomg.validation" }, importOptions = ImportOption.DoNotIncludeTests.class)
public class ModulesArchitectureRulesTest {

    @ArchTest
    static final ArchRule module_dependencies_are_respected = layeredArchitecture().consideringAllDependencies()

        .layer("Domain")
        .definedBy("com.bernardomg.validation.domain..")
        .layer("Validators")
        .definedBy("com.bernardomg.validation.validator..")
        .layer("Constraints")
        .definedBy("com.bernardomg.validation.constraint..")
        .layer("Tests")
        .definedBy("com.bernardomg.validation.test..")

        .whereLayer("Domain")
        .mayOnlyBeAccessedByLayers("Domain", "Tests", "Validators")
        .whereLayer("Validators")
        .mayNotBeAccessedByAnyLayer()
        .whereLayer("Constraints")
        .mayNotBeAccessedByAnyLayer()
        .whereLayer("Tests")
        .mayNotBeAccessedByAnyLayer();

}
