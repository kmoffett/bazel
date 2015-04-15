package(default_visibility = ["//visibility:public"])

filegroup(
    name = "srcs",
    srcs = glob(["**"]),
)

filegroup(
    name = "protoc",
    srcs = ["system_provided/protoc"],
)

java_import(
    name = "aether",
    jars = [
        "aether/aether-api-1.0.0.v20140518.jar",
        "aether/aether-spi-1.0.0.v20140518.jar",
        "aether/aether-transport-http-1.0.0.v20140518.jar",
        "aether/aether-connector-basic-1.0.0.v20140518.jar",
        "aether/aether-transport-classpath-1.0.0.v20140518.jar",
        "aether/aether-transport-wagon-1.0.0.v20140518.jar",
        "aether/aether-impl-1.0.0.v20140518.jar",
        "aether/aether-transport-file-1.0.0.v20140518.jar",
        "aether/aether-util-1.0.0.v20140518.jar",
    ],
)

java_import(
    name = "apache_commons_collections",
    jars = ["system_provided/commons-collections3.jar"],
)

java_import(
    name = "apache_commons_compress",
    jars = ["system_provided/commons-compress.jar"],
)

java_import(
    name = "apache_commons_logging",
    jars = ["system_provided/commons-logging.jar"],
)

java_import(
    name = "apache_httpclient",
    jars = ["system_provided/httpclient.jar"],
)

java_import(
    name = "apache_httpcore",
    jars = ["system_provided/httpcore.jar"],
)

java_import(
    name = "apache_velocity",
    jars = ["apache_velocity/velocity-1.7.jar"],
)

java_import(
    name = "asm",
    jars = ["asm/asm-5.0.3.jar"],
)

java_plugin(
    name = "auto_annotation_plugin",
    processor_class = "com.google.auto.value.processor.AutoAnnotationProcessor",
    deps = [
        ":apache_commons_collections",
        ":apache_velocity",
        ":asm",
        ":auto_common",
        ":auto_service",
        ":auto_value_value",
        ":guava",
        ":jsr305",
        ":tomcat_annotations_api",
    ],
)

java_import(
    name = "auto_common",
    jars = ["auto/auto-common-0.3.jar"],
)

java_import(
    name = "auto_service",
    jars = ["auto/auto-service-1.0-rc2.jar"],
)

java_plugin(
    name = "auto_service_plugin",
    processor_class = "com.google.auto.service.processor.AutoServiceProcessor",
    deps = [
        ":auto_common",
        ":auto_service",
        ":guava",
    ],
)

java_plugin(
    name = "auto_value_plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    deps = [
        ":apache_commons_collections",
        ":apache_velocity",
        ":asm",
        ":auto_common",
        ":auto_service",
        ":auto_value_value",
        ":guava",
        ":jsr305",
        ":tomcat_annotations_api",
    ],
)

java_library(
    name = "auto_value",
    exported_plugins = [
        ":auto_annotation_plugin",
        ":auto_value_plugin",
    ],
    exports = [
        ":auto_value_value",
        ":tomcat_annotations_api",
    ],
)

java_import(
    name = "auto_value_value",
    jars = ["auto/auto-value-1.0.jar"],
)

java_import(
    name = "gson",
    jars = ["system_provided/gson.jar"],
)

java_import(
    name = "guava",
    jars = ["guava/guava-18.0.jar"],
)

java_import(
    name = "joda_time",
    jars = ["system_provided/joda-time.jar"],
)

java_import(
    name = "jsr305",
    jars = ["system_provided/jsr305.jar"],
)

java_import(
    name = "jsr330_inject",
    jars = ["jsr330_inject/javax.inject.jar"],
)

java_import(
    name = "maven_model",
    jars = [
        "maven_model/maven-aether-provider-3.2.3.jar",
        "maven_model/maven-model-3.2.3.jar",
        "maven_model/maven-model-builder-3.2.3.jar",
    ],
)

java_import(
    name = "plexus_interpolation",
    jars = ["system_provided/plexus-interpolation.jar"],
)

java_import(
    name = "plexus_utils",
    jars = ["system_provided/plexus-utils2.jar"],
)

java_import(
    name = "protobuf",
    jars = ["system_provided/protobuf.jar"],
)

java_import(
    name = "tomcat_annotations_api",
    jars = ["system_provided/tomcat8-annotations-api.jar"],
)

# Testing

java_import(
    name = "guava-testlib",
    jars = ["guava/guava-testlib.jar"],
)

java_import(
    name = "junit4",
    jars = [
        "system_provided/hamcrest-core.jar",
        "system_provided/junit4.jar",
    ],
)

java_import(
    name = "mockito",
    jars = ["mockito/mockito-all-1.10.19.jar"],
)

java_import(
    name = "truth",
    jars = ["truth/truth-0.24+.jar"],
)

# To be used by the skylark example.
filegroup(
    name = "junit4-jars",
    srcs = [
        "system_provided/hamcrest-core.jar",
        "system_provided/junit4.jar",
    ],
)
