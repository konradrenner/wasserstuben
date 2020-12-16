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
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;
import javax.persistence.Entity;

/**
 *
 * @author rpri182
 */
@AnalyzeClasses(packages = "org.kore.wg.boundary")
public class JPACodingRulesTest {
    @ArchTest
    ArchRule jpa_entities_must_have_entity_postfix
            = classes().that().areAnnotatedWith(Entity.class)
                    .should().haveNameMatching(".*Entity")
                    .because("we agreed on this convention");

    @ArchTest
    ArchRule jpa_entities_must_have_protected_constructors
            = constructors().that().areDeclaredInClassesThat()
                    .areAnnotatedWith(Entity.class)
                    .should().beProtected()
                    .because("they are managed by JPA");

}
