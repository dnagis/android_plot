LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := samples

# Only compile source java files in this apk.
LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_STATIC_ANDROID_LIBRARIES := androidx.appcompat_appcompat

LOCAL_PACKAGE_NAME := HelloPlot

LOCAL_SDK_VERSION := current

#Failure [INSTALL_FAILED_OLDER_SDK: ... Requires newer sdk version #29 (current version is #27)]
LOCAL_MIN_SDK_VERSION := 26

LOCAL_DEX_PREOPT := false

include $(BUILD_PACKAGE)

# Use the following include to make our test apk.
include $(call all-makefiles-under,$(LOCAL_PATH))
