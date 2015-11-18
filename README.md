XmlTag
=========
The easiest way to simplify custom View tags in layout xmls. 

You like using custom `Views` but hate what they do to your xmls?
This library can take your custom `View` and with a simple `@XmlTag` annotation - simplify your layout file drastically.

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
Currently you can download the code and add it to your project manually.
I'm working on the maven repository upload.

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
