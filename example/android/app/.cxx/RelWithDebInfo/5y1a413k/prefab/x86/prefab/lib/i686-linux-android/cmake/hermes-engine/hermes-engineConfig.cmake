if(NOT TARGET hermes-engine::libhermes)
add_library(hermes-engine::libhermes SHARED IMPORTED)
set_target_properties(hermes-engine::libhermes PROPERTIES
    IMPORTED_LOCATION "/Users/remi/.gradle/caches/transforms-3/e290a9643a9793d8d18dbe6d97e15b71/transformed/jetified-hermes-android-0.72.3-release/prefab/modules/libhermes/libs/android.x86/libhermes.so"
    INTERFACE_INCLUDE_DIRECTORIES "/Users/remi/.gradle/caches/transforms-3/e290a9643a9793d8d18dbe6d97e15b71/transformed/jetified-hermes-android-0.72.3-release/prefab/modules/libhermes/include"
    INTERFACE_LINK_LIBRARIES ""
)
endif()

