XmlTag
=========

[![License](https://img.shields.io/github/license/blipinsk/XmlTag.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-XmlTag-green.svg?style=flat)](http://android-arsenal.com/details/1/2830)
[![Maven Central](https://img.shields.io/maven-central/v/com.bartoszlipinski/xmltag.svg)](http://gradleplease.appspot.com/#xmltag)
[![Bintray](https://img.shields.io/bintray/v/blipinsk/maven/XmlTag.svg)](https://bintray.com/blipinsk/maven/XmlTag/_latestVersion)

The easiest way to simplify custom View tags in layout xmls. 

Version 1.5 of Android Studio introduced the ability to use the short name of the custom `Views` when code-completing:

 ![ ](/sample/screens/first.png)

but why not take it a step further... and actually **use** the short name of the `CustomView` in your layout xml?

 ![ ](/sample/screens/second.png)

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
    compile 'com.bartoszlipinski:xmltag:1.1.1'
    apt 'com.bartoszlipinski:xmltag-compiler:1.1.1'
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
