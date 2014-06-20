// Copyright 2014 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.syntax;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.vfs.PathFragment;

import java.util.List;

/**
 * Syntax node for an import statement.
 */
public final class ImportStatement extends Statement {

  private final ImmutableList<Ident> dir;
  private final ImmutableList<Ident> symbols;
  private final PathFragment importPath;

  /**
   * Constructs an import statement.
   */
  ImportStatement(List<Ident> dir, List<Ident> symbols) {
    this.dir = ImmutableList.copyOf(dir);
    this.symbols = ImmutableList.copyOf(symbols);
    this.importPath = new PathFragment(Joiner.on("/").join(dir));
  }

  public ImmutableList<Ident> getDir() {
    return dir;
  }

  public ImmutableList<Ident> getSymbols() {
    return symbols;
  }

  public PathFragment getImportPath() {
    return importPath;
  }

  @Override
  public String toString() {
    return "from " + Joiner.on(".").join(dir) + " import " + Joiner.on(", ").join(symbols);
  }

  @Override
  void exec(Environment env) throws EvalException, InterruptedException {
    if (!env.isImportAllowed()) {
      throw new EvalException(getLocation(), "imports are not yet allowed in BUILD files");
    }

    for (Ident i : symbols) {
      try {
        if (i.getName().startsWith("_")) {
          throw new EvalException(getLocation(), "symbol '" + i + "' is private and cannot "
              + "be imported");
        }
        env.importSymbol(getImportPath(), i.getName());
      } catch (Environment.NoSuchVariableException e) {
        throw new EvalException(getLocation(), "import failed: " + e.getMessage());
      }
    }
  }

  @Override
  public void accept(SyntaxTreeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  void validate(ValidationEnvironment env) throws EvalException {
    // TODO(bazel-team): implement semantical check.
  }
}