/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.wg;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Checkt gegen eine Whitelist ob nur erlaubte Technologien im Domain-Layer
 * eingesetzt werden (bspw. nur Bean Validation und CDI ist erlaubt, sonst muss
 * es Plain Java sein).
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "org.kore.wg.boundary", importOptions = {ImportOption.DoNotIncludeTests.class})
public class CorrectTechnologyUsageInBoundaryTest {

    @ArchTest
    ArchRule just_jaxrs_is_allowed_in_jaxrs_package = classes()
            .that().resideInAPackage("..jaxrs..")
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("java..",
                    "javax.validation..",
                    "javax.enterprise..",
                    "javax.inject..",
                    "javax.ws.rs..",
                    "javax.servlet..",
                    "org.kore.wg.boundary..jaxrs..",
                    "org.kore.wg.control..",
                    "org.kore.wg.entity..");

    @ArchTest
    ArchRule just_jpa_is_allowed_in_jpa_package = classes()
            .that().resideInAPackage("..jpa..")
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("java..",
                    "javax.validation..",
                    "javax.enterprise..",
                    "javax.inject..",
                    "javax.persistence..",
                    "javax.transaction..",
                    "org.kore.wg.boundary..jpa..",
                    "org.kore.wg.control..",
                    "org.kore.wg.entity..");
}
