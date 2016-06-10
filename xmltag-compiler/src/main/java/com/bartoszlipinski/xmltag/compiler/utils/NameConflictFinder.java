/*
 * Copyright 2015 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.xmltag.compiler.utils;

/**
 * Created by Bartosz Lipinski
 * 18.11.2015
 */
public class NameConflictFinder {
    private static class AndroidViewPackage {
        public static final String NAME = "android.view";
        public static final String[] FORBIDDEN = new String[]{
                "AbsSavedState",
                "AccessibilityInteractionController",
                "AccessibilityIterators",
                "ActionMode",
                "ActionProvider",
                "Choreographer",
                "CollapsibleActionView",
                "ContextMenu",
                "ContextThemeWrapper",
                "Display",
                "DisplayAdjustments",
                "DisplayEventReceiver",
                "DisplayInfo",
                "DisplayListCanvas",
                "DragEvent",
                "FallbackEventHandler",
                "FocusFinder",
                "FocusFinderHelper",
                "FrameInfo",
                "FrameStats",
                "GestureDetector",
                "GhostView",
                "GraphicBuffer",
                "Gravity",
                "HapticFeedbackConstants",
                "HardwareLayer",
                "HardwareRenderer",
                "InflateException",
                "InputChannel",
                "InputDevice",
                "InputEvent",
                "InputEventConsistencyVerifier",
                "InputEventReceiver",
                "InputEventSender",
                "InputFilter",
                "InputQueue",
                "KeyCharacterMap",
                "KeyEvent",
                "LayoutInflater",
                "MagnificationSpec",
                "Menu",
                "MenuInflater",
                "MenuItem",
                "MotionEvent",
                "OrientationEventListener",
                "OrientationListener",
                "PointerIcon",
                "RemotableViewMethod",
                "RenderNode",
                "RenderNodeAnimator",
                "ScaleGestureDetector",
                "SearchEvent",
                "SoundEffectConstants",
                "SubMenu",
                "Surface",
                "SurfaceControl",
                "SurfaceHolder",
                "SurfaceSession",
                "SurfaceView",
                "TextureView",
                "ThreadedRenderer",
                "TouchDelegate",
                "VelocityTracker",
                "View",
                "ViewAnimationUtils",
                "ViewConfiguration",
                "ViewDebug",
                "ViewGroup",
                "ViewGroupOverlay",
                "ViewHierarchyEncoder",
                "ViewManager",
                "ViewOutlineProvider",
                "ViewOverlay",
                "ViewParent",
                "ViewPropertyAnimator",
                "ViewPropertyAnimatorRT",
                "ViewRootImpl",
                "ViewStructure",
                "ViewStub",
                "ViewTreeObserver",
                "Window",
                "WindowAnimationFrameStats",
                "WindowCallbackWrapper",
                "WindowContentFrameStats",
                "WindowId",
                "WindowInfo",
                "WindowInsets",
                "WindowManager",
                "WindowManagerGlobal",
                "WindowManagerImpl",
                "WindowManagerInternal",
                "WindowManagerPolicy"
        };
    }

    private static class AndroidWidgetPackage {
        public static final String NAME = "android.widget";
        public static final String[] FORBIDDEN = new String[]{
                "AbsListView",
                "AbsSeekBar",
                "AbsSpinner",
                "AbsoluteLayout",
                "AccessibilityIterators",
                "ActionMenuPresenter",
                "ActionMenuView",
                "ActivityChooserModel",
                "ActivityChooserView",
                "Adapter",
                "AdapterView",
                "AdapterViewAnimator",
                "AdapterViewFlipper",
                "Advanceable",
                "AlphabetIndexer",
                "AnalogClock",
                "AppSecurityPermissions",
                "ArrayAdapter",
                "AutoCompleteTextView",
                "BaseAdapter",
                "BaseExpandableListAdapter",
                "Button",
                "CalendarView",
                "CalendarViewLegacyDelegate",
                "CalendarViewMaterialDelegate",
                "CheckBox",
                "Checkable",
                "CheckedTextView",
                "Chronometer",
                "CompoundButton",
                "CursorAdapter",
                "CursorFilter",
                "CursorTreeAdapter",
                "DatePicker",
                "DatePickerCalendarDelegate",
                "DatePickerController",
                "DateTimeView",
                "DayPickerPagerAdapter",
                "DayPickerView",
                "DayPickerViewPager",
                "DialerFilter",
                "DigitalClock",
                "DoubleDigitManager",
                "EdgeEffect",
                "EditText",
                "Editor",
                "ExpandableListAdapter",
                "ExpandableListConnector",
                "ExpandableListPosition",
                "ExpandableListView",
                "FastScroller",
                "Filter",
                "FilterQueryProvider",
                "Filterable",
                "FrameLayout",
                "Gallery",
                "GridLayout",
                "GridView",
                "HeaderViewListAdapter",
                "HeterogeneousExpandableList",
                "HorizontalScrollView",
                "ImageButton",
                "ImageSwitcher",
                "ImageView",
                "LinearLayout",
                "ListAdapter",
                "ListPopupWindow",
                "ListView",
                "MediaController",
                "MultiAutoCompleteTextView",
                "NumberPicker",
                "OnDateChangedListener",
                "OverScroller",
                "PopupMenu",
                "PopupWindow",
                "ProgressBar",
                "QuickContactBadge",
                "RadialTimePickerView",
                "RadioButton",
                "RadioGroup",
                "RatingBar",
                "RelativeLayout",
                "RemoteViews",
                "RemoteViewsAdapter",
                "RemoteViewsListAdapter",
                "RemoteViewsService",
                "ResourceCursorAdapter",
                "ResourceCursorTreeAdapter",
                "RtlSpacingHelper",
                "ScrollBarDrawable",
                "ScrollView",
                "Scroller",
                "SearchView",
                "SectionIndexer",
                "SeekBar",
                "ShareActionProvider",
                "SimpleAdapter",
                "SimpleCursorAdapter",
                "SimpleCursorTreeAdapter",
                "SimpleExpandableListAdapter",
                "SimpleMonthView",
                "SlidingDrawer",
                "Space",
                "SpellChecker",
                "Spinner",
                "SpinnerAdapter",
                "StackView",
                "SuggestionsAdapter",
                "Switch",
                "TabHost",
                "TabWidget",
                "TableLayout",
                "TableRow",
                "TextClock",
                "TextSwitcher",
                "TextView",
                "ThemedSpinnerAdapter",
                "TimePicker",
                "TimePickerClockDelegate",
                "TimePickerSpinnerDelegate",
                "Toast",
                "ToggleButton",
                "Toolbar",
                "TwoLineListItem",
                "VideoView",
                "ViewAnimator",
                "ViewFlipper",
                "ViewSwitcher",
                "WrapperListAdapter",
                "YearPickerView",
                "ZoomButton",
                "ZoomButtonsController",
                "ZoomControls"
        };
    }

    private static class AndroidWebkitPackage {
        public static final String NAME = "android.webkit";
        public static final String[] FORBIDDEN = new String[]{
                "CacheManager",
                "ClientCertRequest",
                "ConsoleMessage",
                "CookieManager",
                "CookieSyncManager",
                "DateSorter",
                "DownloadListener",
                "FindActionModeCallback",
                "GeolocationPermissions",
                "HttpAuthHandler",
                "JavascriptInterface",
                "JsDialogHelper",
                "JsPromptResult",
                "JsResult",
                "LegacyErrorStrings",
                "MimeTypeMap",
                "PermissionRequest",
                "Plugin",
                "PluginData",
                "PluginList",
                "PluginStub",
                "SslErrorHandler",
                "URLUtil",
                "UrlInterceptHandler",
                "UrlInterceptRegistry",
                "ValueCallback",
                "WebBackForwardList",
                "WebChromeClient",
                "WebHistoryItem",
                "WebIconDatabase",
                "WebMessage",
                "WebMessagePort",
                "WebResourceError",
                "WebResourceRequest",
                "WebResourceResponse",
                "WebSettings",
                "WebStorage",
                "WebSyncManager",
                "WebView",
                "WebViewClient",
                "WebViewDatabase",
                "WebViewDelegate",
                "WebViewFactory",
                "WebViewFactoryProvider",
                "WebViewFragment",
                "WebViewProvider"
        };
    }

    private static class AndroidAppPackage {
        public static final String NAME = "android.app";
        public static final String[] FORBIDDEN = new String[]{
                "ActionBar",
                "Activity",
                "ActivityGroup",
                "ActivityManager",
                "ActivityManagerInternal",
                "ActivityManagerNative",
                "ActivityOptions",
                "ActivityThread",
                "ActivityTransitionCoordinator",
                "ActivityTransitionState",
                "ActivityView",
                "AlarmManager",
                "AlertDialog",
                "AliasActivity",
                "AppGlobals",
                "AppImportanceMonitor",
                "AppOpsManager",
                "Application",
                "ApplicationErrorReport",
                "ApplicationLoaders",
                "ApplicationPackageManager",
                "ApplicationThreadNative",
                "BackStackRecord",
                "BroadcastOptions",
                "ContextImpl",
                "DatePickerDialog",
                "Dialog",
                "DialogFragment",
                "DownloadManager",
                "EnterTransitionCoordinator",
                "ExitTransitionCoordinator",
                "ExpandableListActivity",
                "Fragment",
                "FragmentBreadCrumbs",
                "FragmentContainer",
                "FragmentController",
                "FragmentHostCallback",
                "FragmentManager",
                "FragmentTransaction",
                "IActivityManager",
                "IApplicationThread",
                "Instrumentation",
                "IntentService",
                "JobSchedulerImpl",
                "KeyguardManager",
                "LauncherActivity",
                "ListActivity",
                "ListFragment",
                "LoadedApk",
                "LoaderManager",
                "LocalActivityManager",
                "MediaRouteActionProvider",
                "MediaRouteButton",
                "NativeActivity",
                "Notification",
                "NotificationManager",
                "OnActivityPausedListener",
                "PackageDeleteObserver",
                "PackageInstallObserver",
                "PendingIntent",
                "Presentation",
                "ProfilerInfo",
                "ProgressDialog",
                "QueuedWork",
                "RemoteInput",
                "ResourcesManager",
                "ResultInfo",
                "SearchDialog",
                "SearchManager",
                "SearchableInfo",
                "Service",
                "SharedElementCallback",
                "SharedPreferencesImpl",
                "StatusBarManager",
                "SystemServiceRegistry",
                "TabActivity",
                "TaskStackBuilder",
                "TimePickerDialog",
                "UiAutomation",
                "UiAutomationConnection",
                "UiModeManager",
                "VoiceInteractor",
                "WallpaperInfo",
                "WallpaperManager"
        };
    }

    public static String findConflictWith(String tag) {
        String conflict;
        conflict = findConflictInAndroidView(tag);
        if (conflict != null) {
            return conflict;
        }
        conflict = findConflictInAndroidWidget(tag);
        if (conflict != null) {
            return conflict;
        }
        conflict = findConflictInAndroidWebkit(tag);
        if (conflict != null) {
            return conflict;
        }
        return findConflictInAndroidApp(tag);
    }

    private static String findConflictInAndroidView(String tag) {
        return contains(AndroidViewPackage.FORBIDDEN, tag) ? AndroidViewPackage.NAME + "." + tag : null;
    }

    private static String findConflictInAndroidWidget(String tag) {
        return contains(AndroidWidgetPackage.FORBIDDEN, tag) ? AndroidWidgetPackage.NAME + "." + tag : null;
    }

    private static String findConflictInAndroidWebkit(String tag) {
        return contains(AndroidWebkitPackage.FORBIDDEN, tag) ? AndroidWebkitPackage.NAME + "." + tag : null;
    }

    private static String findConflictInAndroidApp(String tag) {
        return contains(AndroidAppPackage.FORBIDDEN, tag) ? AndroidAppPackage.NAME + "." + tag : null;
    }

    private static boolean contains(String[] array, String tag) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(tag)) {
                return true;
            }
        }
        return false;
    }
}
