package com.xmage.launcher;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JavaStatusTest {
    @Test
    public void test_oldSystem() {
        assertTrue(JavaStatus.getStatus(
                "1.7.0_65", //We should fail if the system java is 1.7,
                "1.8.0_11", //we require 1.8, and
                "" //we have no local Java
        ) == JavaStatus.Incompatible);
    }

    public void test_newSystem() {
        assertTrue(JavaStatus.getStatus(
                "1.8.0_141-b15", //We should succeed if the system java is a new 1.8
                "1.8.0_11", //we require an old 1.8, and
                "" //we have no local Java
        ) == JavaStatus.SystemCompatible);
    }

    public void test_minorOldSystem() {
        assertTrue(JavaStatus.getStatus(
                "1.8.0_11", //We should fail if we have an old 1.8 installed,
                "1.8.0_141-b15", //we need a new 1.8, and
                "" //we have no local Java
        ) == JavaStatus.Incompatible);
    }

    public void test_newLocal() {
        assertTrue(JavaStatus.getStatus(
                "1.7.0_65", //We should succeed if our system Java is 1.7
                "1.8.0_11", //we need an old 1.8, and
                "1.8.0_141-b15" //we have a newer 1.8 locally
        ) == JavaStatus.LocalCompatible);
    }
}