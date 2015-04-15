Format: 3.0 (quilt)
Source: bazel
Binary: bazel, bazel-docs
Architecture: any all
Version: 0~pre20150410-cc4463-1
Maintainer: Kyle Moffett <kyle@moffetthome.net>
Homepage: http://bazel.io/
Standards-Version: 3.9.6
Vcs-Git: https://github.com/kmoffett/bazel-debian/
Build-Depends: debhelper (>= 9), dh-exec (>= 0.3), openjdk-8-jdk, openjdk-8-source, bc, libarchive-dev, libprotobuf-java, pkg-config, protobuf-compiler, zip, zlib1g-dev
Package-List:
 bazel deb devel optional arch=any
 bazel-docs deb doc optional arch=all
Checksums-Sha1:
 ab36318d1dda27ac07bf59cce84aa89aa818460e 20832125 bazel_0~pre20150410-cc4463.orig.tar.gz
 25c51bb76c6fbd6a8ae9e350deac3d813f9ac806 8512808 bazel_0~pre20150410-cc4463-1.debian.tar.xz
Checksums-Sha256:
 1f7c0cc4a9a988a3a7f8ef415859616539e64a53c3f842688e04bf13f1f7eec8 20832125 bazel_0~pre20150410-cc4463.orig.tar.gz
 f1b948dcac21823a356fa6d2c63096e3b0f706a2596eb631202ef5ef2e99a689 8512808 bazel_0~pre20150410-cc4463-1.debian.tar.xz
Files:
 f016ec4fa8bf393a11065b1adf9030c7 20832125 bazel_0~pre20150410-cc4463.orig.tar.gz
 8404cc10976998e26cb93d5e68d19962 8512808 bazel_0~pre20150410-cc4463-1.debian.tar.xz
