/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.wg;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.*;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.ws.rs.Path;

/**
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "org.kore.wg")
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..entity..")
            .domainServices("..control..")
            .applicationServices("..control..")
            //            .adapter("external", "..boundary.externalservice..")
            .adapter("rest", "..boundary..jaxrs..")
            .adapter("jpa", "..boundary..jpa..");

    @ArchTest
    static final ArchRule jpaentities_must_reside_in_a_jpa_package
            = classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..boundary..jpa..")
                    .as("JPA Entities must reside in a package '..jpa..'");

    @ArchTest
    static final ArchRule jpaembeddables_must_reside_in_a_jpa_package
            = classes().that().areAnnotatedWith(Embeddable.class).should().resideInAPackage("..boundary..jpa..")
                    .as("JPA Embeddables must reside in a package '..jpa..'");

    @ArchTest
    static final ArchRule jpamappedsuperclasses_must_reside_in_a_jpa_package
            = classes().that().areAnnotatedWith(MappedSuperclass.class).should().resideInAPackage("..boundary..jpa..")
                    .as("JPA MappedSuperclasses must reside in a package '..jpa..'");

    @ArchTest
    static final ArchRule jaxrsresources_must_reside_in_a_jaxrs_package
            = classes().that().areAnnotatedWith(Path.class).should().resideInAPackage("..boundary..jaxrs..")
                    .as("JPA Entities should reside in a package '..jpa..'");
}
