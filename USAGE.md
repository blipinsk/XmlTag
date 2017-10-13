Usage
=====

  1. Add `@XmlTag` annotation to your custom `View`

      ```java
      @XmlTag
      public class CustomEditText extends EditText {
          // ...
      }
      ```

      or

      ```java
      @XmlTag("WriteWhatYouWantHere")
      public class CustomEditText extends EditText {
          // ...
      }
      ```

  2. **Optionally**: hit `Build -> Rebuild Project`

  3. Use the specified `XmlTag` in your `xmls` to inflate your custom `View`
      ```xml
      <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent">

          <CustomEditText
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />

      </FrameLayout>
      ```

      or

      ```xml
      <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent">

          <WriteWhatYouWantHere
              android:layout_width="wrap_content"
              android:layout_height="wrap_content" />

      </FrameLayout>
      ```

Including In Your Project
-------------------------
Add this to your **project** gradle dependencies:

```xml
dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
}
```

In your **module** (application) gradle add (right after the `'com.android.application'`)

```xml
apply plugin: 'com.neenbedankt.android-apt'
```

and dependencies (also in your application gradle):

```xml
dependencies {
    compile 'com.bartoszlipinski:xmltag:1.1.3'
    apt 'com.bartoszlipinski:xmltag-compiler:1.1.3'
}
```

License
=======

    Copyright 2015 Bartosz Lipiński

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
