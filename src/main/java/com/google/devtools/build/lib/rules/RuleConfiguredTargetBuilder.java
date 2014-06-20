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
package com.google.devtools.build.lib.rules;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.devtools.build.lib.syntax.Environment;
import com.google.devtools.build.lib.syntax.EvalException;
import com.google.devtools.build.lib.syntax.Function;
import com.google.devtools.build.lib.view.RuleConfiguredTarget;
import com.google.devtools.build.lib.view.RuleContext;

/**
 * A helper class to build Rule Configured Targets via runtime loaded rule implementations
 * defined using the Skylark Build Extension Language. This is experimental code.
 */
public final class RuleConfiguredTargetBuilder {

  /**
   * Create a Rule Configured Target from the ruleContext and the ruleImplementation.
   */
  public static RuleConfiguredTarget buildRule(RuleContext ruleContext,
      ImmutableMap<String, Class<?>> builtInClasses, Function ruleImplementation) {
    SkylarkRuleContext skylarkRuleContext = new SkylarkRuleContext(ruleContext);
    Environment env =
        SkylarkRuleImplementationFunctions.getNewEnvironment(skylarkRuleContext, builtInClasses);
    try {
      Object target = ruleImplementation.call(ImmutableList.<Object>of(skylarkRuleContext),
          ImmutableMap.<String, Object>of(), null, env);

      if (ruleContext.hasErrors()) {
        return null;
      } else if (target instanceof RuleConfiguredTarget) {
        return (RuleConfiguredTarget) target;
      } else {
        ruleContext.ruleError("Rule implementation doesn't return a RuleConfiguredTarget");
        return null;
      }
    } catch (InterruptedException e) {
      ruleContext.ruleError(e.getMessage());
      return null;
    } catch (EvalException e) {
      ruleContext.ruleError("\n" + e.getLocation().print() + ": " + e.getMessage());
      return null;
    }
  }
}