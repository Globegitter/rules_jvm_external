// Copyright 2019 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.bazel;


import android.os.SystemProperties;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.Resetter;

import android.app.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Junit Test using Robolectric with AssertJ matchers.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
  @Test
  public void testOnCreateNotNull() {
    ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
    Activity activity = controller.create().destroy().get();
    assertThat(activity).isNotNull();
  }

  @Test
  public void testCanLoadProperties() {
    ClassLoader cl = SystemProperties.class.getClassLoader();
    URL urlFromCl = cl.getResource("build.prop");
    try (InputStream is = cl.getResourceAsStream("build.prop")) {
      assertThat(is).isNotNull();
    } catch (IOException e) {
      throw new RuntimeException("failed to load build.prop", e);
    }
  }

}
