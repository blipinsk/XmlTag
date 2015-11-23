XmlTag
=========
The easiest way to simplify custom View tags in layout xmls. 

Although Android Studio 1.5 currently let's us use the short name of the custom `Views` when code-completing:

 ![ ](/sample/screens/first.png)

but why not take it a step further... and actually **use** the short name of the `CustomView` in your layout xml?

 ![ ](/sample/screens/second.png)

Before:

    <com.bartoszlipinski.xmltagger.sample.view.CustomView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

After:

    <CustomView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

Usage
=====

  1. Add `@XmlTag` annotation to your custom `View`
  
        @XmlTag
        public class CustomEditText extends EditText {
            // ...
        }
        
    or
    
        @XmlTag("WriteWhatYouWantHere")
        public class CustomEditText extends EditText {
            // ...
        }

  2. **Optionally**: hit `Build -> Rebuild Project`
        
  3. Use the specified `XmlTag` in your `xmls` to inflate your custom `View`

        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        
            <CustomEditText
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
                
        </FrameLayout>
        
    or
    
        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        
            <WriteWhatYouWantHere
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
                
        </FrameLayout>
        
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
    compile 'com.bartoszlipinski:xmltag:1.0.0'
    apt 'com.bartoszlipinski:xmltag-compiler:1.0.0'
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
