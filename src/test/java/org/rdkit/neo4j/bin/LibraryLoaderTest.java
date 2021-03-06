package org.rdkit.neo4j.bin;

/*-
 * #%L
 * RDKit-Neo4j
 * %%
 * Copyright (C) 2019 RDKit
 * %%
 * Copyright (C) 2019 Evgeny Sorokin
 * @@ All Rights Reserved @@
 * This file is part of the RDKit Neo4J integration.
 * The contents are covered by the terms of the BSD license
 * which is included in the file LICENSE, found at the root
 * of the neo4j-rdkit source tree.
 * #L%
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.RDKit.RDKFuncs;
import org.junit.Test;

public class LibraryLoaderTest {

  @Test
  public void testWin() {
    final List<String> WINDOWS = Arrays.asList("Windows 10", "Windows XP", "Windows 7", "Windows 8");
    final String architecture = "86_64";
    final String expectedOs = "win32";
    final String expectedArch = "x86_64";

    for (String win: WINDOWS) {
      String os = LibraryLoader.getOsFormatted(win);
      assertEquals(os, expectedOs);
    }

    String arch = LibraryLoader.getArchFormatted(architecture);
    assertEquals(arch, expectedArch);
  }

  @Test
  public void testMacOs() {
    final String expectedOs = "macosx";
    final String expectedArch = "x86_64";

    // todo: add test for mac os x arch ???
    String os = LibraryLoader.getOsFormatted("Mac OS X");

    assertEquals(expectedOs, os);
  }

  @Test
  public void testLinux() {
    final List<String> ARCHITECTURES = Arrays.asList("i386", "amd64");
    final String linuxOs = "Linux";

    final String expectedOs = "linux";
    final List<String> expectedArchs = Arrays.asList("x86", "x86_64");

    for (int i = 0; i < ARCHITECTURES.size(); i++) {
      String arch = LibraryLoader.getArchFormatted(ARCHITECTURES.get(i));
      assertEquals(expectedArchs.get(i), arch);
    }

    String os = LibraryLoader.getOsFormatted(linuxOs);
    assertEquals(expectedOs, os);
  }

  @Test
  public void testLoadLibraries() throws LoaderException {
    LibraryLoader.loadLibraries();
    String propName = RDKFuncs.getComputedPropName();

    final String expected = "__computedProps";
    assertEquals(expected, propName);
  }
}
