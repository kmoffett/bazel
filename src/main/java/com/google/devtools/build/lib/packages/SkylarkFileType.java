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
package com.google.devtools.build.lib.packages;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.devtools.build.lib.syntax.SkylarkBuiltin;
import com.google.devtools.build.lib.syntax.SkylarkCallable;
import com.google.devtools.build.lib.util.FileType;
import com.google.devtools.build.lib.util.FileType.HasFilename;
import com.google.devtools.build.lib.util.FileTypeSet;

/**
 * A wrapper class for FileType and FileTypeSet functionality in Skylark.
 */
@SkylarkBuiltin(name = "SkylarkFileType", doc = "File type for file filtering.")
public class SkylarkFileType {

  private final FileType fileType;

  private SkylarkFileType(FileType fileType) {
    this.fileType = fileType;
  }

  public static SkylarkFileType of(Iterable<String> extensions) {
    return new SkylarkFileType(FileType.of(extensions));
  }

  public FileTypeSet getFileTypeSet() {
    return FileTypeSet.of(fileType);
  }

  @SkylarkCallable(doc = "")
  public Object filter(Iterable<HasFilename> files) {
    // TODO(bazel-team): this is not nice or efficient, but we need to concat this
    // to an other list in Skylark so it needs to be a list for now.
    return Lists.newArrayList(FileType.filter(files, fileType));
  }

  @SkylarkCallable(doc = "")
  public boolean matches(String fileName) {
    return fileType.apply(fileName);
  }

  @VisibleForTesting
  public Object getExtensions() {
    return fileType.getExtensions();
  }
}