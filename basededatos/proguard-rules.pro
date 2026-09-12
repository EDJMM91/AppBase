
# R8/ProGuard rules para AppBase
# Minimizacion y ofuscacion del APK

# Mantener clases Hilt
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.integration.AutomaticallyInstalledComponent class *

# Mantener clases Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Mantener clases Room
-keep class * extends androidx.room.Entity { *; }
-keep class * extends androidx.room.Dao { *; }
-keepclassmembers class * extends androidx.room.Database { *; }

# Mantener clases Koin
-keep class org.koin.** { *; }

# Mantener clases de Parcelize
-keep class kotlinx.parcelize.** { *; }

# Mantener clases de serializaci�n
-keep class kotlinx.serialization.** { *; }

# Mantener modelos nativos C++
-keepclassmembers class * {
    native <methods>;
}

# Optimizaci�n R8
-optimizations !code/allocation/variable
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Desactivar verificaciones de seguridad para libs conocidas
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**
-ignorewarnings
