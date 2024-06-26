# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep public class com.mitek.build.micall.sdk.core.MiCallFactory{
   public *;
}

-keep public interface com.mitek.build.micall.sdk.listener.publisher.MiCallStateListener{
    *;
}

# Giữ lại lớp MiCallSDK và các phương thức công khai của nó để đảm bảo logic vẫn được thực thi
-keep class com.mitek.build.micall.sdk.core.MiCallSDK {
    public *;
}

-keep public class com.mitek.build.micall.sdk.model.**{
    *;
}
-keep public class org.pjsip.**{
    *;
}

# Loại bỏ các thông tin gỡ lỗi và các phương thức không cần thiết
# Làm xáo trộn và tối ưu hóa các phần còn lại
-dontobfuscate
-dontoptimize
-dontpreverify
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod
#-keepclassmembers public class * {
#    public protected *;
#}

# Chèn các runtime exceptions vào nội dung phương thức
#-assumenosideeffects class com.mitek.build.micall.sdk.core.** {
#    <methods>;
#}

-obfuscationdictionary proguard_dict.txt
# Retrofit2
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembernames interface * {
    @retrofit2.http.* <methods>;
}
-keep public class * extends *
-keep interface * { *; }
-keepattributes Signature

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-dontwarn okio.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
# GSON Annotations
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile