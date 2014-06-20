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
package com.google.devtools.build.lib.skyframe;

import com.google.devtools.build.lib.packages.NoSuchThingException;
import com.google.devtools.build.lib.packages.Target;
import com.google.devtools.build.lib.packages.TargetUtils;
import com.google.devtools.build.lib.syntax.Label;
import com.google.devtools.build.lib.view.DependencyResolver;
import com.google.devtools.build.lib.view.TargetAndConfiguration;
import com.google.devtools.build.lib.view.config.BuildConfigurationCollection;
import com.google.devtools.build.skyframe.Node;
import com.google.devtools.build.skyframe.NodeBuilder.Environment;
import com.google.devtools.build.skyframe.NodeKey;

import javax.annotation.Nullable;

/**
 * A dependency resolver for use within Skyframe. Loads packages lazily when possible.
 */
public final class SkyframeDependencyResolver extends DependencyResolver {

  private final Environment env;

  public SkyframeDependencyResolver(BuildConfigurationCollection configurations,
      Environment env) {
    super(configurations);
    this.env = env;
  }

  @Override
  protected void invalidVisibilityReferenceHook(TargetAndConfiguration node, Label label) {
    env.getListener().error(TargetUtils.getLocationMaybe(node.getTarget()), String.format(
        "Label '%s' in visibility attribute does not refer to a package group", label));
  }

  @Override
  protected void invalidPackageGroupReferenceHook(TargetAndConfiguration node, Label label) {
    env.getListener().error(TargetUtils.getLocationMaybe(node.getTarget()), String.format(
        "label '%s' does not refer to a package group", label));
  }

  @Nullable
  @Override
  protected Target getTarget(Label label) throws NoSuchThingException {
    NodeKey key = PackageNode.key(label.getPackageFragment());
    Node node = env.getDep(key);
    if (node == null) {
      return null;
    }
    PackageNode packageNode = (PackageNode) node;
    return packageNode.getPackage().getTarget(label.getName());
  }
}