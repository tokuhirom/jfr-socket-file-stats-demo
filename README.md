# jfr-socket-file-stats-demo

## Why?

You can observe the (file|socket)'s (reading|writing) bytes by JFR event streaming.
This repository will show you how to use it.

## How it works?

There are `jdk.SocketRead`, `jdk.SocketWrite`, `jdk.FileRead` and `jdk.FileWrite` events in JFR.
You can observe these events via `RecordingStream` class.
And expose it to micrometer.

## When is this method useful?

- The application causes tons of file IO
- The application causes tons of socket IO

## Sample observing result

    # HELP jdk_socket_write_bytes_total
    # TYPE jdk_socket_write_bytes_total counter
    jdk_socket_write_bytes_total{address="127.0.0.1",host="localhost",} 115321.0
    jdk_socket_write_bytes_total{address="0:0:0:0:0:0:0:1",host="",} 175452.0
    jdk_socket_write_bytes_total{address="93.184.216.34",host="example.com",} 115767.0
    # HELP jdk_file_read_bytes_total
    # TYPE jdk_file_read_bytes_total counter
    jdk_file_read_bytes_total{path="/Users/tokuhirom/Library/Java/JavaVirtualMachines/temurin-17/Contents/Home/conf/security/policy/unlimited/default_local.policy",} 193.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/5.3.10/1ff16eb107dd0411deaffa236467efed44d65c60/spring-beans-5.3.10.jar",} 11045.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-actuator-autoconfigure/2.5.5/f5b9d034f74528eb503fbd768170220ab48e4f4c/spring-boot-actuator-autoconfigure-2.5.5.jar",} 12460.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/5.3.10/31984cef4b5d38f2711cf870994c43e89619ff4e/spring-web-5.3.10.jar",} 214872.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/5.3.10/de0e0c07193697df7ba5db6086020447fdbcb24d/spring-aop-5.3.10.jar",} 361.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.12.5/b064cf057f23d3d35390328c5030847efeffedde/jackson-databind-2.12.5.jar",} 322134.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/Library/Java/JavaVirtualMachines/temurin-17/Contents/Home/lib/security/cacerts",} 305308.0
    jdk_file_read_bytes_total{path="/dev/urandom",} 35584.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-actuator/2.5.5/62cb2f7a5d92787c37d9ed2aabbc4b6d1eb18eef/spring-boot-actuator-2.5.5.jar",} 145610.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-webmvc/5.3.10/d8a26ccc0fb4788c05eca148be8ee987d554a9d2/spring-webmvc-5.3.10.jar",} 201520.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient/0.10.0/8d51b0acb3a56aac96da7c990638eccf624a1abe/simpleclient-0.10.0.jar",} 4458.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/Library/Caches/JetBrains/IntelliJIdea2021.2/captureAgent/debugger-agent.jar",} 912.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-core/1.7.4/cdcf50124c3549576fea3d54391c7c3163915e0d/micrometer-core-1.7.4.jar",} 38024.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.module/jackson-module-parameter-names/2.12.5/2c85c2036d0851425a260c01eb5f7ddbed1eeb00/jackson-module-parameter-names-2.12.5.jar",} 2634.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.12.5/725e364cc71b80e60fa450bd06d75cdea7fb2d59/jackson-core-2.12.5.jar",} 32854.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/2.5.5/c6335b6e1aff79b13565890ad833bb8865ae2d50/spring-boot-2.5.5.jar",} 26571.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/Library/Java/JavaVirtualMachines/temurin-17/Contents/Home/lib/security/blocked.certs",} 2488.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.latencyutils/LatencyUtils/2.0.3/769c0b82cb2421c8256300e907298a9410a2a3d3/LatencyUtils-2.0.3.jar",} 1435.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/2.5.5/350511c5612eea8df4ef0f95e2c1b3ff3ea962e8/spring-boot-autoconfigure-2.5.5.jar",} 15803.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-core/9.0.53/3343c5ebe9e66da2f75436569e371920e8db1d55/tomcat-embed-core-9.0.53.jar",} 348602.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/5.3.10/45945cec4c23de39faf98139ad81b59965014ff0/spring-context-5.3.10.jar",} 87135.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-websocket/9.0.53/e73e461aa0da6a1779bba31f90011cfbd4e4233/tomcat-embed-websocket-9.0.53.jar",} 39166.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/Library/Java/JavaVirtualMachines/temurin-17/Contents/Home/conf/security/policy/unlimited/default_US_export.policy",} 146.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.12.5/52d929d5bb21d0186fe24c09624cc3ee4bafc3b3/jackson-annotations-2.12.5.jar",} 20443.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/io.prometheus/simpleclient_common/0.10.0/407f8e2ec94ff29ff6b76715a1c427145d31fdc4/simpleclient_common-0.10.0.jar",} 4869.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-registry-prometheus/1.7.4/7cc097dbfc91c1742fe1efdb95cee44ea5dc8e16/micrometer-registry-prometheus-1.7.4.jar",} 2680.0
    jdk_file_read_bytes_total{path="/Users/tokuhirom/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/5.3.10/4e0b94b1e2d7a7399ecedef03dd676c34133625e/spring-core-5.3.10.jar",} 37686.0
    # HELP jdk_file_write_bytes_total
    # TYPE jdk_file_write_bytes_total counter
    jdk_file_write_bytes_total{path="/var/folders/bv/ht8x2t99097544xbdbfmtf700000gn/T/.*",} 450.0
    jdk_file_write_bytes_total{path="N/A",} 43564.0
    # HELP jdk_socket_read_bytes_total
    # TYPE jdk_socket_read_bytes_total counter
    jdk_socket_read_bytes_total{address="127.0.0.1",host="localhost",} 16675.0
    jdk_socket_read_bytes_total{address="0:0:0:0:0:0:0:1",host="",} 10704.0
    jdk_socket_read_bytes_total{address="93.184.216.34",host="example.com",} 640172.0
